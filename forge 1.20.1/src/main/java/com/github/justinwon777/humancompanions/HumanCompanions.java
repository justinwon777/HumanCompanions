package com.github.justinwon777.humancompanions;

import com.github.justinwon777.humancompanions.core.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.justinwon777.humancompanions.core.ItemInit.*;

@Mod(HumanCompanions.MOD_ID)
public class HumanCompanions {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humancompanions";

    public HumanCompanions() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(eventBus);
        ItemInit.ITEMS.register(eventBus);
        eventBus.addListener(this::buildContents);
        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        PacketHandler.register();
        Config.register();
    }

    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(Arbalist_Spawn_Egg);
            event.accept(Knight_Spawn_Egg);
            event.accept(Archer_Spawn_Egg);
            event.accept(Axeguard_Spawn_Egg);
        }
    }
}
