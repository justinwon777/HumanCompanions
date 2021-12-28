package com.justin.companion.core.event;

import com.justin.companion.Companion;
import com.justin.companion.core.EntityInit;
import com.justin.companion.entity.NPC;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Companion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.NPC.get(), NPC.createAttributes().build());
    }
}
