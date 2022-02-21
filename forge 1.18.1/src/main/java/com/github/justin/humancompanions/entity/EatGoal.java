package com.github.justin.humancompanions.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

public class EatGoal extends Goal {
    protected final HumanCompanionEntity companion;
    ItemStack food = ItemStack.EMPTY;

    public EatGoal(HumanCompanionEntity entity) {
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
        this.companion.setItemSlot(EquipmentSlot.OFFHAND, food);
        companion.startUsingItem(InteractionHand.OFF_HAND);
        companion.setEating(true);
    }

    public void stop() {
        companion.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
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
