package com.justin.companion;

import com.justin.companion.core.EntityInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Companion.MOD_ID)
public class Companion {
    public static final String MOD_ID = "companion";

    public Companion() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(eventBus);
    }

}
