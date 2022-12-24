package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.core.Config;
import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LowHealthGoal extends Goal {
    protected final AbstractHumanCompanionEntity mob;
    int startTick = 0;
    StringTextComponent text = new StringTextComponent("I need food!");
    ItemStack food = ItemStack.EMPTY;

    public LowHealthGoal(AbstractHumanCompanionEntity entity) {
        this.mob = entity;
    }

    public boolean canUse() {
        if (Config.LOW_HEALTH_FOOD.get()) {
            if (this.mob.getHealth() < this.mob.getMaxHealth() / 2 && this.mob.isTame()) {
                food = mob.checkFood();
                return food.isEmpty();
            }
        }
        return false;
    }

    public void start() {
        startTick = this.mob.tickCount;
        if (this.mob.getOwner() != null) {
            this.mob.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.mob.getDisplayName(), text),
                    this.mob.getUUID());
        }
    }

    public void tick() {
        if ((this.mob.tickCount - startTick) % (15 * 20) == 0 && this.mob.tickCount > startTick) {
            if (this.mob.getOwner() != null) {
                this.mob.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.mob.getDisplayName(), text),
                        this.mob.getUUID());
            }
        }

    }
}
