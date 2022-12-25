package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class CompanionEvents {
    @SubscribeEvent
    public static void companionJoin(final LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof AbstractHumanCompanionEntity companion) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            companion.giveExperiencePoints(entity.getExperienceReward(null));
        }
    }
}
