package com.github.justin.humancompanions.client.event;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.client.CompanionScreen;
import com.github.justin.humancompanions.client.renderer.ArcherRenderer;
import com.github.justin.humancompanions.client.renderer.KnightRenderer;
import com.github.justin.humancompanions.core.ContainerInit;
import com.github.justin.humancompanions.core.EntityInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HumanCompanions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents () {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.KnightEntity.get(), KnightRenderer::new);
        event.registerEntityRenderer(EntityInit.ArcherEntity.get(), ArcherRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerInit.COMPANION_INVENTORY.get(), CompanionScreen::new);
    }
}
