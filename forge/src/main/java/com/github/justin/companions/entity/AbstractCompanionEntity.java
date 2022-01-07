package com.github.justin.companions.entity;

import com.github.justin.companions.core.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class AbstractCompanionEntity extends TamableAnimal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT, Items.COOKED_BEEF, Items.COOKED_COD, Items.COOKED_CHICKEN, Items.COOKED_MUTTON, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_SALMON, Items.MELON);
    public SimpleContainer inventory = new SimpleContainer(27);

    public AbstractCompanionEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
//        this.setCanPickUpLoot(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidCreeperGoal(this, Creeper.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(8, new StrollThroughVillageGoal(this, 200));
        this.goalSelector.addGoal(9, new LowHealthGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return EntityInit.KnightEntity.get().create(level);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (!this.level.isClientSide) {
            if (!this.isTame()) {
                if (itemstack.is(Items.COOKED_BEEF)) {
                    this.tame(player);
                    player.sendMessage(new TextComponent("Companion added"), this.getUUID());

                }
            } else {
                if (this.isFood(itemstack)) {
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        this.heal((float)item.getFoodProperties().getNutrition());
                        TextComponent text =
                                new TextComponent("Health: " + this.getHealth() + "/" + this.getMaxHealth());
                        player.sendMessage(text, this.getUUID());
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    this.openGui((ServerPlayer) player);
                }

            return InteractionResult.SUCCESS;
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public void openGui(ServerPlayer player) {
        player.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> ChestMenu.threeRows(p_53124_, p_53125_, this.inventory), this.getDisplayName()));
    }

    public void checkArmor() {
        ItemStack head = this.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = this.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = this.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = this.getItemBySlot(EquipmentSlot.FEET);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.getItem() instanceof ArmorItem) {
                switch (((ArmorItem) itemstack.getItem()).getSlot()) {
                    case HEAD:
                        if (head.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) head.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) head.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                            }
                        }
                        break;
                    case CHEST:
                        if (chest.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) chest.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) chest.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                            }
                        }
                        break;
                    case LEGS:
                        if (legs.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) legs.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) legs.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                            }
                        }
                        break;
                    case FEET:
                        if (feet.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.FEET, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) feet.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.FEET, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) feet.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.FEET, itemstack);
                            }
                        }
                        break;
                }
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("inventory", 9)) {
            this.inventory.fromTag(tag.getList("inventory", 10));
        }
    }

    public boolean isFood(ItemStack p_30440_) {
        Item item = p_30440_.getItem();
        return item.isEdible();
    }


    public boolean hurt(DamageSource p_34288_, float p_34289_) {
        return super.hurt(p_34288_, p_34289_);
    }

//    protected void reassessTameGoals() {
//
//    }
}
