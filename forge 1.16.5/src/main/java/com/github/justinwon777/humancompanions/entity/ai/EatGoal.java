package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class EatGoal extends Goal {
    protected final AbstractHumanCompanionEntity companion;
    ItemStack food = ItemStack.EMPTY;

    public EatGoal(AbstractHumanCompanionEntity entity) {
        this.companion = entity;
    }

    public boolean canUse() {
        if (this.companion.getHealth() < 20) {
            food = companion.checkFood();
            return !food.isEmpty();
        }
        return false;
    }

    public void start() {
        this.companion.setItemSlot(EquipmentSlotType.OFFHAND, food);
        companion.startUsingItem(Hand.OFF_HAND);
        companion.setEating(true);
    }

    public void stop() {
        companion.setItemSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
        companion.setEating(false);
    }

    public void tick () {
        if (this.companion.getHealth() < 20) {
            food = companion.checkFood();
            if (!food.isEmpty()) {
                start();
            }
        }
    }
}