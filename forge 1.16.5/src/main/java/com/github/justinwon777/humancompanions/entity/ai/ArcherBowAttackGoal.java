package com.github.justinwon777.humancompanions.entity.ai;

import com.github.justinwon777.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.BowItem;

import java.util.EnumSet;

public class ArcherBowAttackGoal<T extends AbstractHumanCompanionEntity & IRangedAttackMob> extends Goal{
    private final T mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public ArcherBowAttackGoal(T p_i47515_1_, double p_i47515_2_, int p_i47515_4_, float p_i47515_5_) {
        this.mob = p_i47515_1_;
        this.speedModifier = p_i47515_2_;
        this.attackIntervalMin = p_i47515_4_;
        this.attackRadiusSqr = p_i47515_5_ * p_i47515_5_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        if (mob.isEating()) {
            return false;
        } else {
            return this.mob.getTarget() != null && this.isHoldingBow();
        }
    }

    protected boolean isHoldingBow() {
        return this.mob.isHolding(item -> item instanceof BowItem);
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone()) && this.isHoldingBow();
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            boolean flag = this.mob.getSensing().canSee(livingentity);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }

            if (flag) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (this.mob.isStationery() && this.mob.isGuarding()) {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                if (!flag || d0 > (double) this.attackRadiusSqr) {
                    this.mob.clearTarget();
                }
            }
            else {
                if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else {
                    this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20) {
                    if ((double) this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double) this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }

                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double) (this.attackRadiusSqr * 0.75F)) {
                        this.strafingBackwards = false;
                    } else if (d0 < (double) (this.attackRadiusSqr * 0.25F)) {
                        this.strafingBackwards = true;
                    }

                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
                } else {
                    this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                }
            }

            if (this.mob.isUsingItem()) {
                if (!flag && this.seeTime < -60) {
                    this.mob.stopUsingItem();
                } else if (flag) {
                    int i = this.mob.getTicksUsingItem();
                    if (i >= 20) {
                        this.mob.stopUsingItem();
                        this.mob.performRangedAttack(livingentity, BowItem.getPowerForTime(i));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.mob.startUsingItem(ProjectileHelper.getWeaponHoldingHand(this.mob, item -> item instanceof BowItem));
            }

        }
    }
}
