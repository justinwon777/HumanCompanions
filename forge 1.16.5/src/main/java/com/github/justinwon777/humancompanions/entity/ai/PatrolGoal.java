package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class PatrolGoal extends RandomWalkingGoal {

    protected final float probability;
    public Vector3d patrolVec;
    public AbstractHumanCompanionEntity companion;
    public int radius;

    public PatrolGoal(AbstractHumanCompanionEntity p_25987_, int interval, int radius) {
        this(p_25987_, 1.0D, 0.001F, interval, radius);
    }

    public PatrolGoal(AbstractHumanCompanionEntity p_25990_, double p_25991_, float p_25992_, int interval,
                      int radius) {
        super(p_25990_, p_25991_);
        this.probability = p_25992_;
        this.companion = p_25990_;
        this.interval = interval;
        this.radius = radius;
    }

    public boolean canUse() {
        if (companion.getPatrolPos() == null || !companion.isPatrolling()) {
            return false;
        }
        this.patrolVec = Vector3d.atBottomCenterOf(companion.getPatrolPos());
        return super.canUse();
    }

    @Nullable
    protected Vector3d getPosition() {
        Vector3d vec = getRawPosition();
        if (vec != null) {
            double distance = vec.distanceTo(patrolVec);
            if (distance > radius) {
                vec = null;
            }
        }
        return vec;
    }

    public Vector3d getRawPosition() {
        if (this.mob.isInWaterOrBubble()) {
            Vector3d Vector3d = RandomPositionGenerator.getPos(this.mob, radius, 7);
            return Vector3d == null ? super.getPosition() : Vector3d;
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability ? RandomPositionGenerator.getPos(this.mob, radius, 7) :
                    super.getPosition();
        }
    }
}
