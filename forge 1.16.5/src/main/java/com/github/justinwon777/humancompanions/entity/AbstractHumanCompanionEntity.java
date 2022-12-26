package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.container.CompanionContainer;
import com.github.justinwon777.humancompanions.core.Config;
import com.github.justinwon777.humancompanions.core.EntityInit;
import com.github.justinwon777.humancompanions.core.PacketHandler;
import com.github.justinwon777.humancompanions.entity.ai.*;
import com.github.justinwon777.humancompanions.networking.OpenInventoryPacket;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.*;


public class AbstractHumanCompanionEntity extends TameableEntity{

    private static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(AbstractHumanCompanionEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> SEX = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);
    private static final DataParameter<Integer> BASE_HEALTH = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);
    private static final DataParameter<Integer> EXP_LVL = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);
    private static final DataParameter<Boolean> EATING = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ALERT = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HUNTING = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> PATROLLING = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> FOLLOWING = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> GUARDING = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> STATIONERY = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.BOOLEAN);
    private static final DataParameter<Optional<BlockPos>> PATROL_POS = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.OPTIONAL_BLOCK_POS);
    private static final DataParameter<Integer> PATROL_RADIUS = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);
    private static final DataParameter<String> FOOD1 = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.STRING);
    private static final DataParameter<String> FOOD2 = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.STRING);
    private static final DataParameter<Integer> FOOD1_AMT = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);
    private static final DataParameter<Integer> FOOD2_AMT = EntityDataManager.defineId(AbstractHumanCompanionEntity.class,
            DataSerializers.INT);

    public Inventory inventory = new Inventory(27);
    public EquipmentSlotType[] armorTypes = new EquipmentSlotType[]{EquipmentSlotType.FEET, EquipmentSlotType.LEGS,
            EquipmentSlotType.CHEST, EquipmentSlotType.HEAD};
    public List<NearestAttackableTargetGoal> alertMobGoals = new ArrayList<>();
    public List<NearestAttackableTargetGoal> huntMobGoals = new ArrayList<>();
    public PatrolGoal patrolGoal;
    public MoveBackToPatrolGoal moveBackGoal;
    public int experienceLevel;
    public int totalExperience;
    public float experienceProgress;
    private int lastLevelUpTime;
    private final Map<String, Integer> foodRequirements = new HashMap<>();
    private String food1;
    private String food2;

    public AbstractHumanCompanionEntity(EntityType<? extends TameableEntity> entityType, World level) {
        super(entityType, level);
        this.setTame(false);
        ((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(true);
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
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new EatGoal(this));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new AvoidCreeperGoal(this, CreeperEntity.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(3, new MoveBackToGuardGoal(this));
        this.goalSelector.addGoal(3, new CustomFollowOwnerGoal(this, 1.3D, 8.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new CustomWaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(9, new LowHealthGoal(this));
        this.targetSelector.addGoal(1, new CustomOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CustomOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
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
        this.entityData.define(HUNTING, true);
        this.entityData.define(PATROLLING, false);
        this.entityData.define(FOLLOWING, false);
        this.entityData.define(GUARDING, false);
        this.entityData.define(STATIONERY, false);
        this.entityData.define(PATROL_POS, Optional.empty());
        this.entityData.define(PATROL_RADIUS, 10);
        this.entityData.define(SEX, 0);
        this.entityData.define(BASE_HEALTH, 20);
        this.entityData.define(EXP_LVL, 0);
        this.entityData.define(FOOD1, "");
        this.entityData.define(FOOD2, "");
        this.entityData.define(FOOD1_AMT, 0);
        this.entityData.define(FOOD2_AMT, 0);
    }

    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn,
                                           SpawnReason reason, @Nullable ILivingEntityData spawnDataIn,
                                           @Nullable CompoundNBT dataTag) {
        int baseHealth = Config.BASE_HEALTH.get() + CompanionData.getHealthModifier();
        modifyMaxHealth(baseHealth - 20);
        this.setHealth(this.getMaxHealth());
        setBaseHealth(baseHealth);
        setSex(this.random.nextInt(2));
        setCompanionSkin(this.random.nextInt(CompanionData.skins[getSex()].length));
        setCustomName(new StringTextComponent(CompanionData.getRandomName(getSex())));
        setPatrolPos(this.blockPosition());
        setPatrolling(true);
        setPatrolRadius(15);
        patrolGoal = new PatrolGoal(this, 60, getPatrolRadius());
        moveBackGoal = new MoveBackToPatrolGoal(this, getPatrolRadius());
        this.goalSelector.addGoal(3, moveBackGoal);
        this.goalSelector.addGoal(3, patrolGoal);
        Item[] allFoods = CompanionData.ALL_FOODS;
        food1 = allFoods[random.nextInt(allFoods.length)].getDescription().getString();
        food2 = allFoods[random.nextInt(allFoods.length)].getDescription().getString();
        while (food1.equals(food2)) {
            food2 = allFoods[random.nextInt(allFoods.length)].getDescription().getString();
        }
        foodRequirements.put(food1, random.nextInt(5) + 1);
        foodRequirements.put(food2, random.nextInt(5) + 1);

        if (Config.SPAWN_ARMOR.get()) {
            for (int i = 0; i < 4; i++) {
                EquipmentSlotType armorType = armorTypes[i];
                ItemStack itemstack = CompanionData.getSpawnArmor(armorType);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(i, itemstack);
                }
            }
            checkArmor();
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void addAdditionalSaveData(CompoundNBT tag) {
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
        tag.putInt("baseHealth", this.getBaseHealth());
        tag.putFloat("XpP", this.experienceProgress);
        tag.putInt("XpLevel", this.experienceLevel);
        tag.putInt("XpTotal", this.totalExperience);
        tag.putString("food1", food1);
        tag.putString("food2", food2);
        tag.putInt("food1_amt", foodRequirements.get(food1));
        tag.putInt("food2_amt", foodRequirements.get(food2));
        if (this.getPatrolPos() != null) {
            int[] patrolPos = {this.getPatrolPos().getX(), this.getPatrolPos().getY(), this.getPatrolPos().getZ()};
            tag.putIntArray("patrol_pos", patrolPos);
        }
    }

    public void readAdditionalSaveData(CompoundNBT tag) {
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
        this.setBaseHealth(tag.getInt("baseHealth"));
        this.experienceProgress = tag.getFloat("XpP");
        this.experienceLevel = tag.getInt("XpLevel");
        this.setExpLvl(tag.getInt("XpLevel"));
        this.totalExperience = tag.getInt("XpTotal");
        food1 = tag.getString("food1");
        food2 = tag.getString("food2");
        foodRequirements.put(food1, tag.getInt("food1_amt"));
        foodRequirements.put(food2, tag.getInt("food2_amt"));
        if (tag.getBoolean("Alert")) {
            this.addAlertGoals();
        }
        if (tag.getBoolean("Hunting")) {
            this.addHuntingGoals();
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
        this.setItemSlot(EquipmentSlotType.FEET, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.LEGS, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.CHEST, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        checkArmor();
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity parent) {
        return EntityInit.Knight.get().create(level);
    }

    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (hand == Hand.MAIN_HAND) {
            if (!this.isTame() && !this.level.isClientSide()) {
                if (itemstack.isEdible()) {
                    String itemFood = itemstack.getItem().getDescription().getString();
                    if (ArrayUtils.contains(foodRequirements.keySet().toArray(new String[0]), itemFood)) {
                        if (foodRequirements.get(itemFood) > 0) {
                            itemstack.shrink(1);
                            foodRequirements.put(itemFood, foodRequirements.get(itemFood) - 1);
                            if (foodRequirements.get(food1) <= 0 && foodRequirements.get(food2) <= 0) {
                                this.tame(player);
                                player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                        new StringTextComponent("Thanks!")), this.getUUID());
                                player.sendMessage(new StringTextComponent("Companion added"), this.getUUID());
                                setPatrolPos(null);
                                setPatrolling(false);
                                setFollowing(true);
                                setPatrolRadius(4);
                                patrolGoal.radius = 4;
                                moveBackGoal.radius = 4;
                            } else {
                                player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                        CompanionData.tameFail[this.random.nextInt(CompanionData.tameFail.length)]), this.getUUID());
                            }
                        } else {
                            player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                    CompanionData.ENOUGH_FOOD[this.random.nextInt(CompanionData.ENOUGH_FOOD.length)]), this.getUUID());
                        }
                    } else {
                        player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                CompanionData.WRONG_FOOD[this.random.nextInt(CompanionData.WRONG_FOOD.length)]), this.getUUID());
                    }
                } else {
                    String task =
                            this.getDisplayName().getString().split(" ")[0] + " wants: " + foodRequirements.get(food1) + " " + food1 + ", " + foodRequirements.get(food2) + " "  + food2;
                    player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                            CompanionData.notTamed[this.random.nextInt(CompanionData.notTamed.length)]), this.getUUID());
                    player.sendMessage(new StringTextComponent(task), this.getUUID());
                }
            } else {
                if (this.isAlliedTo(player)) {
                    if(player.isShiftKeyDown()) {
                        if (!this.level.isClientSide()) {
                            if (!this.isOrderedToSit()) {
                                this.setOrderedToSit(true);
                                StringTextComponent text = new StringTextComponent("I'll stay here.");
                                player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                        text), this.getUUID());
                            } else {
                                this.setOrderedToSit(false);
                                StringTextComponent text = new StringTextComponent("I'll move around.");
                                player.sendMessage(new TranslationTextComponent("chat.type.text", this.getDisplayName(),
                                        text), this.getUUID());
                            }
                        }
                    } else {
                        if (!this.level.isClientSide()) {
                            this.openGui((ServerPlayerEntity) player);
                        }
                    }
                }
                return ActionResultType.sidedSuccess(this.level.isClientSide());
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide());
        }
        return super.mobInteract(player, hand);
    }

    public void openGui(ServerPlayerEntity player) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }
        player.nextContainerCounter();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new OpenInventoryPacket(
                player.containerCounter, this.inventory.getContainerSize(), this.getId()));
        player.containerMenu = new CompanionContainer(player.containerCounter, player.inventory, this.inventory);
        player.containerMenu.addSlotListener(player);
        MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(player, player.containerMenu));
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

    public boolean hurt(DamageSource p_34288_, float p_34289_) {
        if (p_34288_.getEntity() instanceof TameableEntity) {
            if (this.isTame() && ((TameableEntity) p_34288_.getEntity()).isTame()) {
                LivingEntity owner1 = ((TameableEntity) p_34288_.getEntity()).getOwner();
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

    @Override
    public ItemStack eat(World world, ItemStack stack) {
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

    public void giveExperiencePoints(int pXpPoints) {
        this.experienceProgress += (float)pXpPoints / (float)this.getXpNeededForNextLevel();
        this.totalExperience = MathHelper.clamp(this.totalExperience + pXpPoints, 0, Integer.MAX_VALUE);

        while(this.experienceProgress < 0.0F) {
            float f = this.experienceProgress * (float)this.getXpNeededForNextLevel();
            if (this.experienceLevel > 0) {
                this.giveExperienceLevels(-1);
                this.experienceProgress = 1.0F + f / (float)this.getXpNeededForNextLevel();
            } else {
                this.giveExperienceLevels(-1);
                this.experienceProgress = 0.0F;
            }
        }

        while(this.experienceProgress >= 1.0F) {
            this.experienceProgress = (this.experienceProgress - 1.0F) * (float)this.getXpNeededForNextLevel();
            this.giveExperienceLevels(1);
            this.experienceProgress /= (float)this.getXpNeededForNextLevel();
        }

    }

    public void giveExperienceLevels(int pLevels) {
        this.experienceLevel += pLevels;
        if (this.experienceLevel < 0) {
            this.experienceLevel = 0;
            this.experienceProgress = 0.0F;
            this.totalExperience = 0;
        }
        setExpLvl(this.experienceLevel);

        if (pLevels > 0 && this.experienceLevel % 5 == 0 && (float)this.lastLevelUpTime < (float)this.tickCount - 100.0F) {
            this.lastLevelUpTime = this.tickCount;
        }

    }

    public int getXpNeededForNextLevel() {
        if (this.experienceLevel >= 30) {
            return 112 + (this.experienceLevel - 30) * 9;
        } else {
            return this.experienceLevel >= 15 ? 37 + (this.experienceLevel - 15) * 5 : 7 + this.experienceLevel * 2;
        }
    }

    public int getExperienceReward(PlayerEntity player) {
        if (!this.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && !this.isSpectator()) {
            int i = this.experienceLevel * 7;
            return Math.min(i, 100);
        } else {
            return 0;
        }
    }

    public void modifyMaxHealth(int change) {
        AttributeModifier HEALTH_MODIFIER = new AttributeModifier("health",
                change, AttributeModifier.Operation.ADDITION);
        ModifiableAttributeInstance attributeinstance = this.getAttribute(Attributes.MAX_HEALTH);
        attributeinstance.addPermanentModifier(HEALTH_MODIFIER);
    }

    public void checkStats() {
        if ((int) this.getMaxHealth() != getBaseHealth() + (getExpLvl() / 3 )) {
            modifyMaxHealth(getBaseHealth() + (getExpLvl() / 3 ) - (int) this.getMaxHealth());
        }
    }

    public void tick() {
        if (!this.level.isClientSide()) {
            checkArmor();
            checkStats();
        }
        super.tick();
    }

    public void setExpLvl(int lvl) {
        this.entityData.set(EXP_LVL, lvl);
    }

    public int getExpLvl() {
        return this.entityData.get(EXP_LVL);
    }

    @Nullable
    public void setPatrolPos(BlockPos position) { this.entityData.set(PATROL_POS, Optional.ofNullable(position)); }

    @Nullable
    public BlockPos getPatrolPos() { return this.entityData.get(PATROL_POS).orElse(null); }

    public void setPatrolRadius(int radius) { this.entityData.set(PATROL_RADIUS, radius); }

    public int getPatrolRadius() { return this.entityData.get(PATROL_RADIUS); }

    public ResourceLocation getResourceLocation() { return CompanionData.skins[getSex()][getCompanionSkin()]; }

    public int getCompanionSkin() { return this.entityData.get(DATA_TYPE_ID); }

    public void setCompanionSkin(int skinIndex) { this.entityData.set(DATA_TYPE_ID, skinIndex); }

    public void setSex(int sex) {
        this.entityData.set(SEX, sex);
    }

    public int getSex() {
        return this.entityData.get(SEX);
    }

    public void setBaseHealth(int health) {
        this.entityData.set(BASE_HEALTH, health);
    }

    public int getBaseHealth() {
        return this.entityData.get(BASE_HEALTH);
    }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    public boolean isAlert() {
        return this.entityData.get(ALERT);
    }

    public boolean isHunting() {
        return this.entityData.get(HUNTING);
    }

    public boolean isPatrolling() {
        return this.entityData.get(PATROLLING);
    }

    public boolean isGuarding() {
        return this.entityData.get(GUARDING);
    }

    public boolean isStationery() {
        return this.entityData.get(STATIONERY);
    }

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
        for (NearestAttackableTargetGoal huntMobGoal : huntMobGoals) {
            this.targetSelector.addGoal(4, huntMobGoal);
        }
    }

    public void removeHuntingGoals() {
        for (NearestAttackableTargetGoal huntMobGoal : huntMobGoals) {
            this.targetSelector.removeGoal(huntMobGoal);
        }
    }
}
