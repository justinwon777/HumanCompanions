package com.github.justin.humancompanions.core;

import com.github.justin.humancompanions.HumanCompanions;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;

public class ConfiguredStructures {
    public static final StructureTemplatePool OAK_POOL;
    public static final StructureTemplatePool OAK_BIRCH_POOL;
    public static final StructureTemplatePool BIRCH_POOL;
    public static final StructureTemplatePool ACACIA_POOL;
    public static final StructureTemplatePool SANDSTONE_POOL;
    public static final StructureTemplatePool SPRUCE_POOL;
    public static final StructureTemplatePool DARKOAK_POOL;

    static {
        OAK_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_oak_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":cabin"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":cabin2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_triple"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":oak_house"), 2)
        ), StructureTemplatePool.Projection.RIGID));
        OAK_BIRCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_oak_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_birch_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":oak_house"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":birch_house"), 2)

        ), StructureTemplatePool.Projection.RIGID));
        BIRCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_birch_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":birch_house"), 2)
        ), StructureTemplatePool.Projection.RIGID));
        ACACIA_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_acacia_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":acacia_house"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        SPRUCE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_darkoak"), 3),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":cabin"), 3),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":cabin2"), 3),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_triple"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":spruce_house"), 2)
        ), StructureTemplatePool.Projection.RIGID));
        SANDSTONE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":fortified_desert"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":sandstone_house"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        DARKOAK_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_darkoak"), 3),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_double"), 1)
        ), StructureTemplatePool.Projection.RIGID));
    }
    public static ConfiguredStructureFeature<?, ?> Configured_Oak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> OAK_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_Oak_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> OAK_BIRCH_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> BIRCH_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_Acacia_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> ACACIA_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_Sandstone_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> SANDSTONE_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_Spruce_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> SPRUCE_POOL, 1));
    public static ConfiguredStructureFeature<?, ?> Configured_DarkOak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> SPRUCE_POOL, 1));

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
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
    }
}
