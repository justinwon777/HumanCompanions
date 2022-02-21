package com.github.justinwon777.humancompanions.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class KnightMeleeAttackGoal extends MeleeAttackGoal {

    protected final HumanCompanionEntity companion;

    public KnightMeleeAttackGoal(HumanCompanionEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
        super(p_i1636_1_, p_i1636_2_, p_i1636_4_);
        this.companion = p_i1636_1_;
    }

    @Override
    public boolean canUse() {
        if (!this.companion.getCompanionType().equals("knight")) {
            return false;
        }
        return super.canUse();
    }
}
