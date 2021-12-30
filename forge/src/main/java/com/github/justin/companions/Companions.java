package com.github.justin.companions;

import com.github.justin.companions.core.EntityInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Companions.MOD_ID)
public class Companions {
    public static final String MOD_ID = "companions";

    public Companions() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(eventBus);
    }

}
