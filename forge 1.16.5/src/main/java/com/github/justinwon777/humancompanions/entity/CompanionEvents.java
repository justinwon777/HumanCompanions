package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class CompanionEvents {
    @SubscribeEvent
    public static void giveExperience(final LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) event.getSource().getEntity();
            companion.giveExperiencePoints(entity.getExperienceReward(null));
        }
    }

    @SubscribeEvent
    public static void friendlyFire(final LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity) {
            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) event.getSource().getEntity();
            if (companion.isTame()) {
                if (!Config.FRIENDLY_FIRE_PLAYER.get()) {
                    if (event.getEntity() instanceof PlayerEntity) {
                        if (companion.getOwner() == event.getEntity()) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
                if (!Config.FRIENDLY_FIRE_COMPANIONS.get()) {
                    if (event.getEntity() instanceof TameableEntity) {
                        TameableEntity entity = (TameableEntity) event.getEntity();
                        if (entity.isTame()) {
                            LivingEntity owner1 = entity.getOwner();
                            LivingEntity owner2 = companion.getOwner();
                            if (owner1 == owner2) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
