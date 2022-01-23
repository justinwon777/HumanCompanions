package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.core.EntityInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;


public class AbstractHumanCompanionEntity extends TameableEntity {
    public Inventory inventory = new Inventory(27);
    public EquipmentSlotType[] armorTypes = new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS,
            EquipmentSlotType.CHEST, EquipmentSlotType.HEAD};
    public static final StringTextComponent[] tameFail = new StringTextComponent[] {
            new StringTextComponent("I need more food."),
            new StringTextComponent("Is that all you got?"),
            new StringTextComponent("I'm still hungry."),
            new StringTextComponent("Can I have some more?"),
            new StringTextComponent("I'm going to need a bit more."),
            new StringTextComponent("That's not enough."),
    };
    public static final StringTextComponent[] notTamed = new StringTextComponent[]{
            new StringTextComponent("Do you have any food?"),
            new StringTextComponent("I'm hungry"),
            new StringTextComponent("I'm starving"),
            new StringTextComponent("Have you seen any food around here?"),
            new StringTextComponent("I could use some food"),
            new StringTextComponent("I wish I had some food"),
    };

    public AbstractHumanCompanionEntity(EntityType<? extends TameableEntity> entityType, World level) {
        super(entityType, level);
        this.setTame(false);
        ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new AvoidCreeperGoal(this, CreeperEntity.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(9, new LowHealthGoal(this));
        this.targetSelector.addGoal(1, new CustomOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CustomOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D);
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity parent) {
        return EntityInit.KnightEntity.get().create(level);
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (!this.level.isClientSide && hand == Hand.MAIN_HAND) {
            if (!this.isTame()) {
                if (itemstack.isEdible()) {
                    itemstack.shrink(1);
                    if (this.random.nextInt(4) == 0) {
                        this.tame(player);
                        player.sendMessage(new StringTextComponent("Companion added"), this.getUUID());
                    } else {
                        player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                tameFail[this.random.nextInt(tameFail.length)]), this.getUUID());
                    }
                } else {
                    player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                            notTamed[this.random.nextInt(notTamed.length)]), this.getUUID());
                }
            } else {
                if(player.isShiftKeyDown()) {
                    if (!this.isOrderedToSit()) {
                        this.setOrderedToSit(true);
                        StringTextComponent text = new StringTextComponent("I'll stay here.");
                        player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                text), this.getUUID());
                    } else {
                        this.setOrderedToSit(false);
                        StringTextComponent text = new StringTextComponent("I'll follow you.");
                        player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                text), this.getUUID());
                    }
                } else if (itemstack.isEdible()) {
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.abilities.instabuild) {
                            itemstack.shrink(1);
                        }
                        this.heal((float)item.getFoodProperties().getNutrition());
                        StringTextComponent text =
                                new StringTextComponent("Health: " + this.getHealth() + "/" + this.getMaxHealth());
                        player.sendMessage(text, this.getUUID());
                        return ActionResultType.SUCCESS;
                    }
                } else {
                    this.openGui((ServerPlayerEntity) player);
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public void openGui(ServerPlayerEntity player) {
        player.openMenu(new SimpleNamedContainerProvider((p_53124_, p_53125_, p_53126_) -> ChestContainer.threeRows(p_53124_, p_53125_,
                this.inventory), this.getDisplayName()));
    }

    public void checkArmor() {
        ItemStack head = this.getItemBySlot(EquipmentSlotType.HEAD);
        ItemStack chest = this.getItemBySlot(EquipmentSlotType.CHEST);
        ItemStack legs = this.getItemBySlot(EquipmentSlotType.LEGS);
        ItemStack feet = this.getItemBySlot(EquipmentSlotType.FEET);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.getItem() instanceof ArmorItem) {
                switch (((ArmorItem) itemstack.getItem()).getSlot()) {
                    case HEAD:
                        if (head.isEmpty()) {
                            this.setItemSlot(EquipmentSlotType.HEAD, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) head.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlotType.HEAD, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterial.NETHERITE && ((ArmorItem) head.getItem()).getMaterial() != ArmorMaterial.NETHERITE) {
                                this.setItemSlot(EquipmentSlotType.HEAD, itemstack);
                            }
                        }
                        break;
                    case CHEST:
                        if (chest.isEmpty()) {
                            this.setItemSlot(EquipmentSlotType.CHEST, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) chest.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlotType.CHEST, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterial.NETHERITE && ((ArmorItem) chest.getItem()).getMaterial() != ArmorMaterial.NETHERITE) {
                                this.setItemSlot(EquipmentSlotType.CHEST, itemstack);
                            }
                        }
                        break;
                    case LEGS:
                        if (legs.isEmpty()) {
                            this.setItemSlot(EquipmentSlotType.LEGS, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) legs.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlotType.LEGS, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterial.NETHERITE && ((ArmorItem) legs.getItem()).getMaterial() != ArmorMaterial.NETHERITE) {
                                this.setItemSlot(EquipmentSlotType.LEGS, itemstack);
                            }
                        }
                        break;
                    case FEET:
                        if (feet.isEmpty()) {
                            this.setItemSlot(EquipmentSlotType.FEET, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) feet.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlotType.FEET, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterial.NETHERITE && ((ArmorItem) feet.getItem()).getMaterial() != ArmorMaterial.NETHERITE) {
                                this.setItemSlot(EquipmentSlotType.FEET, itemstack);
                            }
                        }
                        break;
                }
            }
        }
    }

    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.put("inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("inventory", 9)) {
            this.inventory.fromTag(tag.getList("inventory", 10));
        }
        this.setItemSlot(EquipmentSlotType.FEET, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.LEGS, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.CHEST, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
        checkArmor();
    }

    public boolean hurt(DamageSource p_34288_, float p_34289_) {
        if (p_34288_.getEntity() instanceof TameableEntity) {
            if (this.isTame() && ((TameableEntity) p_34288_.getEntity()).isTame()) {
                if (this.getOwner().is(((TameableEntity) p_34288_.getEntity()).getOwner())) {
                    return false;
                }
            }
        }
        hurtArmor(p_34288_, p_34289_);
        return super.hurt(p_34288_, p_34289_);
    }

    public void hurtArmor(DamageSource p_150073_, float p_150074_) {
        if (!(p_150074_ <= 0.0F)) {
            p_150074_ /= 4.0F;
            if (p_150074_ < 1.0F) {
                p_150074_ = 1.0F;
            }

            for(ItemStack itemstack : this.getArmorSlots()) {
                if ((!p_150073_.isFire() || !itemstack.getItem().isFireResistant()) && itemstack.getItem() instanceof ArmorItem) {
                    itemstack.hurtAndBreak((int)p_150074_, this, (p_35997_) -> {
                        p_35997_.broadcastBreakEvent(((ArmorItem) itemstack.getItem()).getSlot());
                    });
                }
            }

        }
    }

    public void die(DamageSource source) {
        super.die(source);
    }

    protected void dropEquipment() {
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                this.spawnAtLocation(itemstack);
            }
        }
    }

    public boolean doHurtTarget(Entity entity) {
        ItemStack itemstack = this.getMainHandItem();
        System.out.println(itemstack);
        if (!this.level.isClientSide && !itemstack.isEmpty() && entity instanceof LivingEntity) {
            itemstack.hurtAndBreak(1, this, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
            if (this.getMainHandItem().isEmpty()) {
                StringTextComponent broken = new StringTextComponent("My sword broke!");
                if (this.isTame()) {
                    this.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                            broken), this.getUUID());
                }
            }
        }
        return super.doHurtTarget(entity);
    }

    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn,
                                           SpawnReason reason, @Nullable ILivingEntityData spawnDataIn,
                                           @Nullable CompoundNBT dataTag) {
        for (int i = 0; i < 4; i++) {
            EquipmentSlotType armorType = armorTypes[i];
            ItemStack itemstack = getSpawnArmor(armorType);
            if(!itemstack.isEmpty()) {
                this.inventory.setItem(i, itemstack);
                checkArmor();
            }
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public ItemStack getSpawnArmor(EquipmentSlotType armorType) {
        float materialFloat = this.random.nextFloat();
        if (materialFloat <= 0.4F) {
            return ItemStack.EMPTY;
        } else if(materialFloat < 0.70F) {
            switch(armorType) {
                case HEAD:
                    return Items.LEATHER_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.LEATHER_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.LEATHER_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.LEATHER_BOOTS.getDefaultInstance();
            }
        } else if(materialFloat < 0.90F) {
            switch(armorType) {
                case HEAD:
                    return Items.CHAINMAIL_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.CHAINMAIL_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.CHAINMAIL_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.CHAINMAIL_BOOTS.getDefaultInstance();
            }
        } else {
            switch(armorType) {
                case HEAD:
                    return Items.IRON_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.IRON_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.IRON_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.IRON_BOOTS.getDefaultInstance();
            }
        }
        return ItemStack.EMPTY;
    }
}
