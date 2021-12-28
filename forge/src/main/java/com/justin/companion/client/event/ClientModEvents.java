package com.justin.companion.client.event;

import com.justin.companion.Companion;
import com.justin.companion.client.renderer.NPCRenderer;
import com.justin.companion.core.EntityInit;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Companion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    private ClientModEvents () {}

//    @SubscribeEvent
//    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(ModelLayers.PLAYER, PlayerModel::);
//    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.NPC.get(), NPCRenderer::new);
    }
}
