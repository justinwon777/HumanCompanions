package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;

public class MoveBackToPatrolGoal extends Goal {
    public Vector3d patrolVec;
    public AbstractHumanCompanionEntity companion;
    public int radius;

    public MoveBackToPatrolGoal(AbstractHumanCompanionEntity p_25568_, int radius) {
        this.companion = p_25568_;
        this.radius = radius;
    }

    public boolean canUse() {
        if (this.companion.getPatrolPos() == null || !companion.isPatrolling()) {
            return false;
        }
        this.patrolVec = Vector3d.atBottomCenterOf(this.companion.getPatrolPos());
        Vector3d currentVec = Vector3d.atBottomCenterOf(this.companion.blockPosition());
        if (patrolVec.distanceTo(currentVec) <= radius) {
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
