package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class CustomWaterAvoidingRandomStrollGoal extends WaterAvoidingRandomStrollGoal {

    AbstractHumanCompanionEntity companion;

    public CustomWaterAvoidingRandomStrollGoal(AbstractHumanCompanionEntity p_25987_, double p_25988_) {
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
