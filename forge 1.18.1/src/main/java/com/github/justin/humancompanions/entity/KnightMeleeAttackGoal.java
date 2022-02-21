package com.github.justin.humancompanions.entity;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class KnightMeleeAttackGoal extends MeleeAttackGoal {

    protected final HumanCompanionEntity companion;

    public KnightMeleeAttackGoal(HumanCompanionEntity p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
        this.companion = p_25552_;
    }

    @Override
    public boolean canUse() {
        if (!this.companion.getCompanionType().equals("knight")) {
            return false;
        }
        return super.canUse();
    }
}
