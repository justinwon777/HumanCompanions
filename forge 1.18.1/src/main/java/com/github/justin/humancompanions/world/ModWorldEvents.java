package com.github.justin.humancompanions.world;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.entity.AbstractHumanCompanionEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void companionJoin(final EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof AbstractHumanCompanionEntity) {
//            System.out.println(((AbstractHumanCompanionEntity) event.getEntity()).getCompanionSkin());
        }
    }
}
