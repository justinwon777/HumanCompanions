package com.github.justinwon777.humancompanions.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LowHealthGoal extends Goal {
    protected final TameableEntity mob;
    int startTick = 0;
    StringTextComponent text = new StringTextComponent("My health is low!");

    public LowHealthGoal(TameableEntity entity) {
        this.mob = entity;
    }

    public boolean canUse() {
        if (this.mob.getHealth() < 10 && this.mob.isTame()) {
            return true;
        } else {
            return false;
        }
    }

    public void start() {
        startTick = this.mob.tickCount;
        this.mob.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.mob.getDisplayName(), text),
                this.mob.getUUID());
    }

    public void tick() {
        if ((this.mob.tickCount - startTick) % (15 * 20) == 0 && this.mob.tickCount > startTick) {
            this.mob.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.mob.getDisplayName(),
                            text),
                    this.mob.getUUID());
        }

    }
}
