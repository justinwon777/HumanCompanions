package com.github.justinwon777.humancompanions.world;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.entity.HumanCompanionEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void companionJoin(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof HumanCompanionEntity) {
            System.out.println(((HumanCompanionEntity) event.getEntity()).getCompanionSkin());
        }
    }
}
