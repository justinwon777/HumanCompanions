package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class CompanionEvents {
    @SubscribeEvent
    public static void companionJoin(final LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            AbstractHumanCompanionEntity companion = (AbstractHumanCompanionEntity) event.getSource().getEntity();
            companion.giveExperiencePoints(entity.getExperienceReward(null));
        }
    }
}
