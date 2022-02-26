package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class ConfiguredStructures {
    public static final JigsawPattern OAK_POOL;
    public static final JigsawPattern OAK_BIRCH_POOL;
    public static final JigsawPattern BIRCH_POOL;
    public static final JigsawPattern ACACIA_POOL;
    public static final JigsawPattern SANDSTONE_POOL;
    public static final JigsawPattern SPRUCE_POOL;
    public static final JigsawPattern DARKOAK_POOL;
    public static final JigsawPattern TERRACOTTA_POOL;
    public static final JigsawPattern COMPANIONS_POOL;
    static {
        OAK_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_oak_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":cabin"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":cabin2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_triple"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":oak_house"), 2)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        OAK_BIRCH_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_oak_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_birch_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":oak_house"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":birch_house"), 2)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        BIRCH_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_birch_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":birch_house"), 2)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        ACACIA_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_acacia_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":acacia_house"), 1)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        SPRUCE_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_darkoak"), 3),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":cabin"), 3),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":cabin2"), 3),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_triple"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_double"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":spruce_house"), 2)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        SANDSTONE_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":fortified_desert"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":sandstone_house"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":desert"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":desert_double"), 2)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        DARKOAK_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_darkoak"), 3),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_double"), 1)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        TERRACOTTA_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":terracotta1"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":terracotta2"), 2),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":terracotta_double"), 1)
        ), JigsawPattern.PlacementBehaviour.RIGID));
        COMPANIONS_POOL = JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(HumanCompanions.MOD_ID,
                "companions"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":companions/knight"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":companions/archer"), 1),
                Pair.of(JigsawPiece.legacy(HumanCompanions.MOD_ID + ":companions/arbalist"), 1)
        ), JigsawPattern.PlacementBehaviour.RIGID));
    }
    public static StructureFeature<?, ?> Configured_Oak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> OAK_POOL, 1));
    public static StructureFeature<?, ?> Configured_Oak_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> OAK_BIRCH_POOL, 1));
    public static StructureFeature<?, ?> Configured_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> BIRCH_POOL, 1));
    public static StructureFeature<?, ?> Configured_Acacia_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> ACACIA_POOL, 1));
    public static StructureFeature<?, ?> Configured_Sandstone_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> SANDSTONE_POOL, 1));
    public static StructureFeature<?, ?> Configured_Spruce_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> SPRUCE_POOL, 1));
    public static StructureFeature<?, ?> Configured_DarkOak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> DARKOAK_POOL, 1));
    public static StructureFeature<?, ?> Configured_Terracotta_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new VillageConfig(() -> TERRACOTTA_POOL, 1));

    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Oak_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Oak_Birch_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Birch_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Acacia_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Sandstone_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Spruce_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_DarkOak_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Terracotta_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Oak_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Oak_Birch_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Birch_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Acacia_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Sandstone_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Spruce_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_DarkOak_House);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureInit.COMPANION_HOUSE.get(), Configured_Terracotta_House);
    }
}
