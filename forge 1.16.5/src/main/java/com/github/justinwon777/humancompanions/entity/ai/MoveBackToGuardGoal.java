package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

public class MoveBackToGuardGoal extends Goal {

    public AbstractHumanCompanionEntity companion;
    public Vector3d patrolVec;

    public MoveBackToGuardGoal(AbstractHumanCompanionEntity p_25990_) {
        this.companion = p_25990_;
    }

    public boolean canUse() {
        if (this.companion.getPatrolPos() == null || !companion.isGuarding()) {
            return false;
        }
        this.patrolVec = Vector3d.atBottomCenterOf(this.companion.getPatrolPos());
        Vector3d currentVec = Vector3d.atBottomCenterOf(this.companion.blockPosition());
        if (patrolVec.distanceTo(currentVec) <= 1) {
            return false;
        }
        return true;
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    public void tick() {
        if (companion.getTarget() == null) {
            companion.getNavigation().moveTo(patrolVec.x, patrolVec.y, patrolVec.z, 1.0);
        }
    }

}