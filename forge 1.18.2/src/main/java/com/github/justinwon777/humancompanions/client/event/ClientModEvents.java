package com.github.justinwon777.humancompanions.client.event;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.client.renderer.CompanionRenderer;
import com.github.justinwon777.humancompanions.core.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents () {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.Knight.get(), CompanionRenderer::new);
        event.registerEntityRenderer(EntityInit.Archer.get(), CompanionRenderer::new);
        event.registerEntityRenderer(EntityInit.Arbalist.get(), CompanionRenderer::new);
        event.registerEntityRenderer(EntityInit.Axeguard.get(), CompanionRenderer::new);
    }
}
