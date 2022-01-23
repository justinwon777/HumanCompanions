package com.github.justinwon777.humancompanions.core.event;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.core.EntityInit;
import com.github.justinwon777.humancompanions.entity.ArcherEntity;
import com.github.justinwon777.humancompanions.entity.KnightEntity;
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
