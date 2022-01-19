package com.github.justin.companions.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class KnightEntity extends AbstractCompanionEntity{

    public KnightEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
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
}
