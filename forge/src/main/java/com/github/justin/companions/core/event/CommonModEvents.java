package com.github.justin.companions.core.event;

import com.github.justin.companions.Companions;
import com.github.justin.companions.core.EntityInit;
import com.github.justin.companions.entity.CompanionEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Companions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.CompanionEntity.get(), CompanionEntity.createAttributes().build());
    }
}
