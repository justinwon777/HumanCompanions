package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class CustomWaterAvoidingRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal {
    AbstractHumanCompanionEntity companion;

    public CustomWaterAvoidingRandomWalkingGoal(AbstractHumanCompanionEntity p_25987_, double p_25988_) {
        super(p_25987_, p_25988_);
        this.companion = p_25987_;
    }

    public boolean canUse() {
        if (!companion.isFollowing()) {
            return false;
        }
        return super.canUse();
    }
}
