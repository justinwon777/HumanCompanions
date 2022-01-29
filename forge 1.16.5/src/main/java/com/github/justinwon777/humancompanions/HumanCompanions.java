package com.github.justinwon777.humancompanions;

import com.github.justinwon777.humancompanions.client.renderer.ArcherRenderer;
import com.github.justinwon777.humancompanions.client.renderer.KnightRenderer;
import com.github.justinwon777.humancompanions.core.ConfiguredStructures;
import com.github.justinwon777.humancompanions.core.EntityInit;
import com.github.justinwon777.humancompanions.core.StructureInit;
import com.mojang.serialization.Codec;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod(HumanCompanions.MOD_ID)
public class HumanCompanions
{
    public static final String MOD_ID = "humancompanions";
    private static final Logger LOGGER = LogManager.getLogger();

    public HumanCompanions() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityInit.ENTITIES.register(eventBus);
        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            StructureInit.setupStructures();
            ConfiguredStructures.registerConfiguredStructures();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KnightEntity.get(), KnightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.ArcherEntity.get(), ArcherRenderer::new);
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        if (key.compareTo(Biomes.PLAINS) == 0 || key.compareTo(Biomes.SUNFLOWER_PLAINS) == 0 || key.compareTo(Biomes.JUNGLE) == 0 || key.compareTo(Biomes.JUNGLE_EDGE) == 0 || key.compareTo(Biomes.JUNGLE_HILLS) == 0 || key.compareTo(Biomes.BAMBOO_JUNGLE_HILLS) == 0 || key.compareTo(Biomes.BAMBOO_JUNGLE) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Oak_House);
        } else if (key.compareTo(Biomes.BIRCH_FOREST) == 0 || key.compareTo(Biomes.BIRCH_FOREST_HILLS) == 0 || key.compareTo(Biomes.TALL_BIRCH_FOREST) == 0 || key.compareTo(Biomes.TALL_BIRCH_HILLS) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Birch_House);
        } else if (key.compareTo(Biomes.FOREST) == 0 || key.compareTo(Biomes.FLOWER_FOREST) == 0 || key.compareTo(Biomes.WOODED_HILLS) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Oak_Birch_House);
        } else if (key.compareTo(Biomes.DESERT) == 0 || key.compareTo(Biomes.DESERT_HILLS) == 0 || key.compareTo(Biomes.DESERT_LAKES) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Sandstone_House);
        } else if (key.compareTo(Biomes.SAVANNA) == 0 || key.compareTo(Biomes.SAVANNA_PLATEAU) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Acacia_House);
        } else if (key.compareTo(Biomes.SNOWY_TAIGA) == 0 || key.compareTo(Biomes.SNOWY_TAIGA_HILLS) == 0 || key.compareTo(Biomes.SNOWY_TAIGA_MOUNTAINS) == 0 || key.compareTo(Biomes.TAIGA) == 0 || key.compareTo(Biomes.TAIGA_HILLS) == 0 || key.compareTo(Biomes.TAIGA_MOUNTAINS) == 0 || key.compareTo(Biomes.GIANT_SPRUCE_TAIGA) == 0 || key.compareTo(Biomes.GIANT_SPRUCE_TAIGA_HILLS) == 0 || key.compareTo(Biomes.GIANT_TREE_TAIGA) == 0 || key.compareTo(Biomes.GIANT_TREE_TAIGA_HILLS) == 0) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructures.Configured_Spruce_House);
        }
    }

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                HumanCompanions.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using " +
                        "Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(StructureInit.COMPANION_HOUSE.get(),
                    DimensionStructuresSettings.DEFAULTS.get(StructureInit.COMPANION_HOUSE.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
