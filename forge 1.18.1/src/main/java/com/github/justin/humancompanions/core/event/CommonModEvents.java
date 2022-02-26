package com.github.justin.humancompanions.core.event;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.core.EntityInit;
import com.github.justin.humancompanions.entity.Arbalist;
import com.github.justin.humancompanions.entity.Archer;
import com.github.justin.humancompanions.entity.Knight;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.Knight.get(), Knight.createAttributes().build());
        event.put(EntityInit.Archer.get(), Archer.createAttributes().build());
        event.put(EntityInit.Arbalist.get(), Arbalist.createAttributes().build());
    }
}
