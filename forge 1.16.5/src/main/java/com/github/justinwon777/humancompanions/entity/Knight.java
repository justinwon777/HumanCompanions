package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.Config;
import com.github.justinwon777.humancompanions.core.TagsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class Knight extends AbstractHumanCompanionEntity {

    public Knight(EntityType<? extends TameableEntity> entityType, World level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
    }

    public boolean isSword(ItemStack stack) {
        return stack.getItem().is(TagsInit.Items.SWORDS) | (!stack.getItem().is(TagsInit.Items.AXES) & stack.getItem() instanceof SwordItem);
    }

    public void checkSword() {
        ItemStack hand = this.getItemBySlot(EquipmentSlotType.MAINHAND);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (isSword(itemstack)) {
                if (hand.isEmpty()) {
                    this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
                } else if (isSword(hand)) {
                    if (getTotalAttackDamage(itemstack) > getTotalAttackDamage(hand)) {
                        this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
                    }
                }
            }
        }
    }

    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        checkSword();
    }

    public void tick() {if (!this.level.isClientSide()) {
        checkSword();
    }
        super.tick();
    }

    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn,
                                           SpawnReason reason, @Nullable ILivingEntityData spawnDataIn,
                                           @Nullable CompoundNBT dataTag) {
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
