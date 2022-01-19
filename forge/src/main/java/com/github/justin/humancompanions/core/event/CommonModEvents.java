package com.github.justin.humancompanions.core.event;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.core.EntityInit;
import com.github.justin.humancompanions.entity.ArcherEntity;
import com.github.justin.humancompanions.entity.KnightEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.KnightEntity.get(), KnightEntity.createAttributes().build());
        event.put(EntityInit.ArcherEntity.get(), ArcherEntity.createAttributes().build());
    }
}
