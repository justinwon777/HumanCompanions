package com.github.justinwon777.humancompanions.entity.ai;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.EnumSet;
import java.util.function.Predicate;

public class AvoidCreeperGoal<T extends LivingEntity> extends Goal {
    protected final TameableEntity mob;
    private final double walkSpeedModifier;
    private final double sprintSpeedModifier;
    protected T toAvoid;
    protected final float maxDist;
    protected Path path;
    protected final PathNavigator pathNav;
    protected final Class<T> avoidClass;
    protected final Predicate<LivingEntity> avoidPredicate;
    protected final Predicate<LivingEntity> predicateOnAvoidEntity;
    private final EntityPredicate avoidEntityTargeting;

    public AvoidCreeperGoal(TameableEntity p_25027_, Class<T> p_25028_, float p_25029_, double p_25030_, double p_25031_) {
        this(p_25027_, p_25028_, (p_25052_) -> true, p_25029_, p_25030_, p_25031_, EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test);
    }

    public AvoidCreeperGoal(TameableEntity p_25040_, Class<T> p_25041_, Predicate<LivingEntity> p_25042_, float p_25043_,
                            double p_25044_, double p_25045_, Predicate<LivingEntity> p_25046_) {
        this.mob = p_25040_;
        this.avoidClass = p_25041_;
        this.avoidPredicate = p_25042_;
        this.maxDist = p_25043_;
        this.walkSpeedModifier = p_25044_;
        this.sprintSpeedModifier = p_25045_;
        this.predicateOnAvoidEntity = p_25046_;
        this.pathNav = p_25040_.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.avoidEntityTargeting = (new EntityPredicate()).range((double)p_25043_).selector(p_25046_.and(p_25042_));
    }

    public boolean canUse() {
        this.toAvoid = this.mob.level.getNearestLoadedEntity(this.avoidClass, this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().inflate(this.maxDist, 3.0D, this.maxDist));
        if (this.toAvoid == null) {
            return false;
        } else {
            Vector3d vector3d = RandomPositionGenerator.getPosAvoid(this.mob, 16, 7, this.toAvoid.position());
            if (vector3d == null) {
                return false;
            } else if (this.toAvoid.distanceToSqr(vector3d.x, vector3d.y, vector3d.z) < this.toAvoid.distanceToSqr(this.mob)) {
                return false;
            } else {
                this.path = this.pathNav.createPath(vector3d.x, vector3d.y, vector3d.z, 0);
                return this.path != null;
            }
        }
    }

    public boolean canContinueToUse() {
        return !this.pathNav.isDone();
    }

    public void start() {
        StringTextComponent text = new StringTextComponent("Creeper!");
        if (this.mob.isTame()) {
            if (this.mob.blockPosition().closerThan(this.mob.getOwner().blockPosition(), 15)) {
                this.mob.getOwner().sendMessage(new TranslationTextComponent("chat.type.text", this.mob.getDisplayName(), text),
                        this.mob.getUUID());
            }
        }
        this.pathNav.moveTo(this.path, this.walkSpeedModifier);
    }

    public void stop() {
        this.toAvoid = null;
    }

    public void tick() {
        if (this.mob.distanceToSqr(this.toAvoid) < 49.0D) {
            this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
        } else {
            this.mob.getNavigation().setSpeedModifier(this.walkSpeedModifier);
        }

    }
}
