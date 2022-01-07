package com.github.justin.companions.client.event;

import com.github.justin.companions.Companions;
import com.github.justin.companions.client.renderer.ArcherRenderer;
import com.github.justin.companions.client.renderer.KnightRenderer;
import com.github.justin.companions.core.EntityInit;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Companions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    private ClientModEvents () {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.KnightEntity.get(), KnightRenderer::new);
        event.registerEntityRenderer(EntityInit.ArcherEntity.get(), ArcherRenderer::new);
    }
}
