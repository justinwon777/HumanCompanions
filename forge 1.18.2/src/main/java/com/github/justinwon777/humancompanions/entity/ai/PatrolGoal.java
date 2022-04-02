package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class PatrolGoal extends RandomStrollGoal {

    protected final float probability;
    public Vec3 patrolVec;
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
        this.patrolVec = Vec3.atBottomCenterOf(companion.getPatrolPos());
        return super.canUse();
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vec = getRawPosition();
        if (vec != null) {
            double distance = vec.distanceTo(patrolVec);
            if (distance > radius) {
                vec = null;
            }
        }
        return vec;
    }

    public Vec3 getRawPosition() {
        if (this.mob.isInWaterOrBubble()) {
            Vec3 vec3 = LandRandomPos.getPos(this.mob, radius, 7);
            return vec3 == null ? super.getPosition() : vec3;
        } else {
            return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, radius, 7) :
                    super.getPosition();
        }
    }
}
