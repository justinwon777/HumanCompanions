package com.github.justin.humancompanions.entity.ai;

import com.github.justin.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class MoveBackToPatrolGoal extends Goal {
    public Vec3 patrolVec;
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
        this.patrolVec = Vec3.atBottomCenterOf(this.companion.getPatrolPos());
        Vec3 currentVec = Vec3.atBottomCenterOf(this.companion.blockPosition());
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