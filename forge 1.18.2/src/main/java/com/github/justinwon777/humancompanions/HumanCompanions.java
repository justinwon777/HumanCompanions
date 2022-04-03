package com.github.justinwon777.humancompanions;

import com.github.justinwon777.humancompanions.core.*;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Mod(HumanCompanions.MOD_ID)
public class HumanCompanions {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "humancompanions";

    public HumanCompanions() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(eventBus);
        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        PacketHandler.register();
        Config.register();
        eventBus.addListener(this::setup);

    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ConfiguredStructures::registerConfiguredStructures);
    }

}
