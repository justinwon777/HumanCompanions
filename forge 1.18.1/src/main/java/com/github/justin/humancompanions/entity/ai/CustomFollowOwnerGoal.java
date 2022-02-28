package com.github.justin.humancompanions.entity.ai;

import com.github.justin.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;

public class CustomFollowOwnerGoal extends FollowOwnerGoal {

    public AbstractHumanCompanionEntity companion;

    public CustomFollowOwnerGoal(AbstractHumanCompanionEntity p_25294_, double p_25295_, float p_25296_, float p_25297_, boolean p_25298_) {
        super(p_25294_, p_25295_, p_25296_, p_25297_, p_25298_);
        this.companion = p_25294_;
    }

    public boolean canUse() {
        if (!companion.isFollowing()) {
            return false;
        }
        return super.canUse();
    }
}
