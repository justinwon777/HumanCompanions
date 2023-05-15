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
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Axeguard extends AbstractHumanCompanionEntity {

    public Axeguard(EntityType<? extends TameableEntity> entityType, World level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
    }

    public boolean isAxe(ItemStack stack) {
        return stack.getItem().is(TagsInit.Items.AXES) | (!stack.getItem().is(TagsInit.Items.SWORDS) & stack.getItem() instanceof AxeItem);
    }

    public void checkAxe() {
        ItemStack hand = this.getItemBySlot(EquipmentSlotType.MAINHAND);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (isAxe(itemstack)) {
                if (hand.isEmpty()) {
                    this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
                } else if (isAxe(hand)) {
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
        checkAxe();
    }

    public void tick() {
        if (!this.level.isClientSide()) {
            checkAxe();
        }
        super.tick();
    }

    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn,
                                           SpawnReason reason, @Nullable ILivingEntityData spawnDataIn,
                                           @Nullable CompoundNBT dataTag) {
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
