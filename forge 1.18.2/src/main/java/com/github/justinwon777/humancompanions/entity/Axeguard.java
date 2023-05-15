package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.core.Config;
import com.github.justinwon777.humancompanions.core.TagsInit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class Axeguard extends AbstractHumanCompanionEntity {

    public Axeguard(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
    }

    public boolean isAxe(ItemStack stack) {
    	return stack.is(TagsInit.Items.AXES) || (!stack.is(TagsInit.Items.SWORDS) && stack.getItem() instanceof AxeItem);
    }

    public void checkAxe() {
        ItemStack hand = this.getItemBySlot(EquipmentSlot.MAINHAND);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (isAxe(itemstack)) {
                if (hand.isEmpty()) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                } else if (isAxe(hand)) {
                    if (getTotalAttackDamage(itemstack) > getTotalAttackDamage(hand)) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                    }
                }
            }
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        checkAxe();
    }

    public void tick() {
        if (!this.level.isClientSide()) {
            checkAxe();
        }
        super.tick();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
                                        MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn,
                                        @Nullable CompoundTag dataTag) {
        if (Config.SPAWN_WEAPON.get()) {
            ItemStack itemstack = getSpawnAxe();
            if (!itemstack.isEmpty()) {
                this.inventory.setItem(4, itemstack);
                checkAxe();
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public ItemStack getSpawnAxe() {
        float materialFloat = this.random.nextFloat();
        if(materialFloat < 0.5F) {
            return Items.WOODEN_AXE.getDefaultInstance();
        } else if(materialFloat < 0.90F) {
            return Items.STONE_AXE.getDefaultInstance();
        } else {
            return Items.IRON_AXE.getDefaultInstance();
        }
    }
}