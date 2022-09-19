package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

public class LowHealthGoal extends Goal {
    protected final AbstractHumanCompanionEntity mob;
    int startTick = 0;
    Component text = Component.literal("I need food!");
    ItemStack food = ItemStack.EMPTY;

    public LowHealthGoal (AbstractHumanCompanionEntity entity) {
        this.mob = entity;
    }

    public boolean canUse() {
        if (this.mob.getHealth() < 10 && this.mob.isTame()) {
            food = mob.checkFood();
            return food.isEmpty();
        }
        return false;

    }

    public void start() {
        startTick = this.mob.tickCount;
        if (this.mob.getOwner() != null) {
            this.mob.getOwner().sendSystemMessage(Component.translatable("chat.type.text", this.mob.getDisplayName(),
                            text));
        }
    }

    public void tick() {
        if ((this.mob.tickCount - startTick) % (15 * 20) == 0 && this.mob.tickCount > startTick) {
            if (this.mob.getOwner() != null) {
                this.mob.getOwner().sendSystemMessage(Component.translatable("chat.type.text", this.mob.getDisplayName(),
                                text));
            }
        }

    }
}
