package com.github.justin.humancompanions.client.event;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.client.renderer.ArcherRenderer;
import com.github.justin.humancompanions.client.renderer.KnightRenderer;
import com.github.justin.humancompanions.core.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents () {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.KnightEntity.get(), KnightRenderer::new);
        event.registerEntityRenderer(EntityInit.ArcherEntity.get(), ArcherRenderer::new);
    }
}
