package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.core.Config;
import com.github.justinwon777.humancompanions.core.EntityInit;
import com.github.justinwon777.humancompanions.core.PacketHandler;
import com.github.justinwon777.humancompanions.entity.ai.*;
import com.github.justinwon777.humancompanions.networking.OpenInventoryPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractHumanCompanionEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SEX = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ALERT = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HUNTING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PATROLLING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FOLLOWING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GUARDING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STATIONERY =
            SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<BlockPos>> PATROL_POS = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Integer> PATROL_RADIUS = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);

    public SimpleContainer inventory = new SimpleContainer(27);
    public EquipmentSlot[] armorTypes = new EquipmentSlot[]{EquipmentSlot.FEET, EquipmentSlot.LEGS,
            EquipmentSlot.CHEST, EquipmentSlot.HEAD};
    public List<NearestAttackableTargetGoal> alertMobGoals = new ArrayList<>();
    public List<NearestAttackableTargetGoal> huntMobGoals = new ArrayList<>();
    public PatrolGoal patrolGoal;
    public MoveBackToPatrolGoal moveBackGoal;
    public int tameIdx = 5;

    public AbstractHumanCompanionEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false);
//        this.setCanPickUpLoot(true);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        for (int i = 0; i < CompanionData.alertMobs.length; i++) {
            alertMobGoals.add(new NearestAttackableTargetGoal(this, CompanionData.alertMobs[i], false));
        }
        for (int i = 0; i < CompanionData.huntMobs.length; i++) {
            huntMobGoals.add(new NearestAttackableTargetGoal(this, CompanionData.huntMobs[i], false));
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new EatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new AvoidCreeperGoal(this, Creeper.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(3, new MoveBackToGuardGoal(this));
        this.goalSelector.addGoal(3, new CustomFollowOwnerGoal(this, 1.3D, 8.0F, 2.5F, false));
        this.goalSelector.addGoal(5, new CustomWaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(9, new LowHealthGoal(this));
        this.targetSelector.addGoal(1, new CustomOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CustomOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 1);
        this.entityData.define(EATING, false);
        this.entityData.define(ALERT, false);
        this.entityData.define(HUNTING, false);
        this.entityData.define(PATROLLING, false);
        this.entityData.define(FOLLOWING, false);
        this.entityData.define(GUARDING, false);
        this.entityData.define(STATIONERY, false);
        this.entityData.define(PATROL_POS, Optional.empty());
        this.entityData.define(PATROL_RADIUS, 10);
        this.entityData.define(SEX, 0);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
                                        MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn,
                                        @Nullable CompoundTag dataTag) {
        AttributeModifier SPAWN_HEALTH_MODIFIER = new AttributeModifier("health",
                CompanionData.getHealthModifier(), AttributeModifier.Operation.ADDITION);
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MAX_HEALTH);
        attributeinstance.addPermanentModifier(SPAWN_HEALTH_MODIFIER);
        this.setHealth(this.getMaxHealth());
        setSex(this.random.nextInt(2));
        setCompanionSkin(this.random.nextInt(CompanionData.skins[getSex()].length));
        setCustomName(new TextComponent(CompanionData.getRandomName(getSex())));
        setPatrolPos(this.blockPosition());
        setPatrolling(true);
        setPatrolRadius(15);
        patrolGoal = new PatrolGoal(this, 60, getPatrolRadius());
        moveBackGoal = new MoveBackToPatrolGoal(this, getPatrolRadius());
        this.goalSelector.addGoal(3, moveBackGoal);
        this.goalSelector.addGoal(3, patrolGoal);

        if (Config.SPAWN_ARMOR.get()) {
            for (int i = 0; i < 4; i++) {
                EquipmentSlot armorType = armorTypes[i];
                ItemStack itemstack = CompanionData.getSpawnArmor(armorType);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(i, itemstack);
                }
            }
            checkArmor();
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("inventory", this.inventory.createTag());
        tag.putInt("skin", this.getCompanionSkin());
        tag.putBoolean("Eating", this.isEating());
        tag.putBoolean("Alert", this.isAlert());
        tag.putBoolean("Hunting", this.isHunting());
        tag.putBoolean("Patrolling", this.isPatrolling());
        tag.putBoolean("Following", this.isFollowing());
        tag.putBoolean("Guarding", this.isGuarding());
        tag.putBoolean("Stationery", this.isStationery());
        tag.putInt("radius", this.getPatrolRadius());
        tag.putInt("sex", this.getSex());
        if (this.getPatrolPos() != null) {
            int[] patrolPos = {this.getPatrolPos().getX(), this.getPatrolPos().getY(), this.getPatrolPos().getZ()};
            tag.putIntArray("patrol_pos", patrolPos);
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setCompanionSkin(tag.getInt("skin"));
        this.setEating(tag.getBoolean("Eating"));
        this.setAlert(tag.getBoolean("Alert"));
        this.setHunting(tag.getBoolean("Hunting"));
        this.setPatrolling(tag.getBoolean("Patrolling"));
        this.setFollowing(tag.getBoolean("Following"));
        this.setGuarding(tag.getBoolean("Guarding"));
        this.setStationery(tag.getBoolean("Stationery"));
        this.setPatrolRadius(tag.getInt("radius"));
        this.setSex(tag.getInt("sex"));
        if (tag.getBoolean("Alert")) {
            addAlertGoals();
        }
        if (tag.getBoolean("Hunting")) {
            addHuntingGoals();
        }
        if (tag.contains("inventory", 9)) {
            this.inventory.fromTag(tag.getList("inventory", 10));
        }
        if (tag.contains("patrol_pos")) {
            int[] positions = tag.getIntArray("patrol_pos");
            setPatrolPos(new BlockPos(positions[0], positions[1], positions[2]));
        }
        if (tag.contains("radius")) {
            patrolGoal = new PatrolGoal(this, 60, tag.getInt("radius"));
            moveBackGoal = new MoveBackToPatrolGoal(this, tag.getInt("radius"));
            this.goalSelector.addGoal(3, moveBackGoal);
            this.goalSelector.addGoal(3, patrolGoal);
        }
        this.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        checkArmor();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return EntityInit.Knight.get().create(level);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND) {
            if (!this.isTame() && !this.level.isClientSide()) {
                if (itemstack.isEdible()) {
                    itemstack.shrink(1);
                    if (this.random.nextInt(tameIdx) == 0) {
                        this.tame(player);
                        player.sendMessage(new TextComponent("Companion added"), this.getUUID());
                        setPatrolPos(null);
                        setPatrolling(false);
                        setFollowing(true);
                        setPatrolRadius(4);
                        patrolGoal.radius = 4;
                        moveBackGoal.radius = 4;
                    } else {
                        if (tameIdx > 1) {
                            tameIdx--;
                        }
                        player.sendMessage(new TranslatableComponent("chat.type.text", this.getDisplayName(),
                                CompanionData.tameFail[this.random.nextInt(CompanionData.tameFail.length)]), this.getUUID());
                    }
                } else {
                    player.sendMessage(new TranslatableComponent("chat.type.text", this.getDisplayName(),
                            CompanionData.notTamed[this.random.nextInt(CompanionData.notTamed.length)]), this.getUUID());
                }
            } else {
                if (this.isAlliedTo(player)) {
                    if(player.isShiftKeyDown()) {
                        if(!this.level.isClientSide()) {
                            if (!this.isOrderedToSit()) {
                                this.setOrderedToSit(true);
                                TextComponent text = new TextComponent("I'll stand here.");
                                player.sendMessage(new TranslatableComponent("chat.type.text", this.getDisplayName(),
                                        text), this.getUUID());
                            } else {
                                this.setOrderedToSit(false);
                                TextComponent text = new TextComponent("I'll move around.");
                                player.sendMessage(new TranslatableComponent("chat.type.text", this.getDisplayName(),
                                        text), this.getUUID());
                            }
                        }
                    } else {
                        if(!this.level.isClientSide()) {
                            this.openGui((ServerPlayer) player);
                        }
                    }
                }
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    public void openGui(ServerPlayer player) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }
        player.nextContainerCounter();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new OpenInventoryPacket(
                player.containerCounter, this.inventory.getContainerSize(), this.getId()));
        player.containerMenu = new CompanionContainer(player.containerCounter, player.getInventory(), this.inventory);
        player.initMenu(player.containerMenu);
        MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
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

    public boolean hurt(DamageSource p_34288_, float p_34289_) {
        if (p_34288_.getEntity() instanceof TamableAnimal) {
            if (this.isTame() && ((TamableAnimal) p_34288_.getEntity()).isTame()) {
                LivingEntity owner1 = ((TamableAnimal) p_34288_.getEntity()).getOwner();
                LivingEntity owner2 = this.getOwner();
                if (owner1 == owner2) {
                    if (!Config.FRIENDLY_FIRE.get()) {
                        return false;
                    }
                }
            }
        }

        if (p_34288_ == DamageSource.FALL && !Config.FALL_DAMAGE.get()) {
            return false;
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
        if (!this.level.isClientSide && !itemstack.isEmpty() && entity instanceof LivingEntity) {
            itemstack.hurtAndBreak(1, this, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            if (this.getMainHandItem().isEmpty()) {
                TextComponent broken = new TextComponent("My sword broke!");
                if (this.isTame()) {
                    this.getOwner().sendMessage(new TranslatableComponent("chat.type.text", this.getDisplayName(),
                            broken), this.getUUID());
                }
            }
        }
        return super.doHurtTarget(entity);
    }

    @Override
    public ItemStack eat(Level world, ItemStack stack) {
        if (stack.isEdible()) {
            this.heal(stack.getItem().getFoodProperties().getNutrition());
        }
        super.eat(world, stack);
        return stack;
    }

    public ItemStack checkFood() {
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.isEdible()) {
                if ((float)itemstack.getItem().getFoodProperties().getNutrition() + this.getHealth() <= this.getMaxHealth()) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public void clearTarget() {
        this.setTarget(null);
    }

    public void setPatrolPos(BlockPos position) {
        this.entityData.set(PATROL_POS, Optional.ofNullable(position));
    }

    @Nullable
    public BlockPos getPatrolPos() {
        return this.entityData.get(PATROL_POS).orElse(null);
    }

    public void setPatrolRadius(int radius) {
        this.entityData.set(PATROL_RADIUS, radius);
    }

    public int getPatrolRadius() {
        return this.entityData.get(PATROL_RADIUS);
    }

    public ResourceLocation getResourceLocation() {
        return CompanionData.skins[getSex()][getCompanionSkin()];
    }

    public int getCompanionSkin() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public void setCompanionSkin(int skinIndex) {
        this.entityData.set(DATA_TYPE_ID, skinIndex);
    }

    public void setSex(int sex) {
        this.entityData.set(SEX, sex);
    }

    public int getSex() { return this.entityData.get(SEX); }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    public boolean isAlert() {
        return this.entityData.get(ALERT);
    }

    public boolean isHunting() {
        return this.entityData.get(HUNTING);
    }

    public boolean isPatrolling() { return this.entityData.get(PATROLLING); }

    public boolean isGuarding() { return this.entityData.get(GUARDING); }

    public boolean isStationery() { return this.entityData.get(STATIONERY); }

    public boolean isFollowing() {
        return this.entityData.get(FOLLOWING);
    }

    public void setEating(boolean eating) {
        this.entityData.set(EATING, eating);
    }

    public void setAlert(boolean alert) {
        this.entityData.set(ALERT, alert);
    }

    public void setHunting(boolean hunting) {
        this.entityData.set(HUNTING, hunting);
    }

    public void setPatrolling(boolean patrolling) {
        this.entityData.set(PATROLLING, patrolling);
    }

    public void setGuarding(boolean guarding) {
        this.entityData.set(GUARDING, guarding);
    }

    public void setStationery(boolean stationery) {
        this.entityData.set(STATIONERY, stationery);
    }

    public void setFollowing(boolean following) {
        this.entityData.set(FOLLOWING, following);
    }

    public void addAlertGoals() {
        for (NearestAttackableTargetGoal alertMobGoal : alertMobGoals) {
            this.targetSelector.addGoal(4, alertMobGoal);
        }
    }

    public void removeAlertGoals() {
        for (NearestAttackableTargetGoal alertMobGoal : alertMobGoals) {
            this.targetSelector.removeGoal(alertMobGoal);
        }
    }

    public void addHuntingGoals() {
        for (int i = 0; i < huntMobGoals.size(); i++) {
            this.targetSelector.addGoal(4, huntMobGoals.get(i));
        }
    }

    public void removeHuntingGoals() {
        for (int i = 0; i < huntMobGoals.size(); i++) {
            this.targetSelector.removeGoal(huntMobGoals.get(i));
        }
    }

}
