package com.github.justinwon777.humancompanions;

import com.github.justinwon777.humancompanions.core.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HumanCompanions.MOD_ID)
public class HumanCompanions {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humancompanions";

    public HumanCompanions() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(eventBus);
        ItemInit.ITEMS.register(eventBus);
        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        PacketHandler.register();
        Config.register();
        eventBus.addListener(this::setup);

    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ConfiguredStructures::registerConfiguredStructures);
    }

}
