package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.Config;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class CompanionEvents {
    @SubscribeEvent
    public static void giveExperience(final LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity companion) {
            companion.giveExperiencePoints(event.getEntity().getExperienceReward());
        }
    }

    @SubscribeEvent
    public static void friendlyFire(final LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity companion && companion.isTame()) {
            if (!Config.FRIENDLY_FIRE_PLAYER.get()) {
                if (event.getEntity() instanceof Player player) {
                    if (companion.getOwner() == player) {
                        event.setCanceled(true);
                        return;
                    }
                }
            }
            if (!Config.FRIENDLY_FIRE_COMPANIONS.get()) {
                if (event.getEntity() instanceof TamableAnimal entity) {
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
