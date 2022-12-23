package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.core.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class Knight extends AbstractHumanCompanionEntity {

    public Knight(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
    }

    public void checkSword() {
        ItemStack hand = this.getItemBySlot(EquipmentSlot.MAINHAND);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.getItem() instanceof SwordItem) {
                if (hand.isEmpty()) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                } else if (itemstack.getItem() instanceof SwordItem && hand.getItem() instanceof SwordItem) {
                    if (((SwordItem) itemstack.getItem()).getDamage() > ((SwordItem) hand.getItem()).getDamage()) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                    }
                }
            }
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        checkSword();
    }

    public void tick() {
        checkArmor();
        checkSword();
        super.tick();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
                                        MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn,
                                        @Nullable CompoundTag dataTag) {
        if (Config.SPAWN_WEAPON.get()) {
            ItemStack itemstack = getSpawnSword();
            if (!itemstack.isEmpty()) {
                this.inventory.setItem(4, itemstack);
                checkSword();
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public ItemStack getSpawnSword() {
        float materialFloat = this.random.nextFloat();
        if(materialFloat < 0.5F) {
            return Items.WOODEN_SWORD.getDefaultInstance();
        } else if(materialFloat < 0.90F) {
            return Items.STONE_SWORD.getDefaultInstance();
        } else {
            return Items.IRON_SWORD.getDefaultInstance();
        }
    }
}