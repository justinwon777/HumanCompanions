package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;

public class ConfiguredStructures {
    public static final Holder<StructureTemplatePool> OAK_POOL;
    public static final Holder<StructureTemplatePool> OAK_BIRCH_POOL;
    public static final Holder<StructureTemplatePool> BIRCH_POOL;
    public static final Holder<StructureTemplatePool> ACACIA_POOL;
    public static final Holder<StructureTemplatePool> SANDSTONE_POOL;
    public static final Holder<StructureTemplatePool> SPRUCE_POOL;
    public static final Holder<StructureTemplatePool> DARKOAK_POOL;
    public static final Holder<StructureTemplatePool> TERRACOTTA_POOL;
    public static final Holder<StructureTemplatePool> COMPANIONS_POOL;
    public static final TagKey<Biome> HAS_OAK_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/oak_house"));
    public static final TagKey<Biome> HAS_OAK_BIRCH_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/oak_birch_house"));
    public static final TagKey<Biome> HAS_BIRCH_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/birch_house"));
    public static final TagKey<Biome> HAS_SANDSTONE_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/sandstone_house"));
    public static final TagKey<Biome> HAS_ACACIA_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/acacia_house"));
    public static final TagKey<Biome> HAS_SPRUCE_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/spruce_house"));
    public static final TagKey<Biome> HAS_DARKOAK_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/darkoak_house"));
    public static final TagKey<Biome> HAS_TERRACOTTA_HOUSE = TagKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HumanCompanions.MOD_ID, "has_structure/terracotta_house"));

    static {
        OAK_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID, "oak_pool"),
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
        OAK_BIRCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "oak_birch_pool"),
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
        BIRCH_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "birch_pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_birch_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":birch_house"), 2)
        ), StructureTemplatePool.Projection.RIGID));
        ACACIA_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "acacia_pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed1"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed2"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed3"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_mixed_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_acacia_double"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":acacia_house"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        SPRUCE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "spruce_pool"),
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
        SANDSTONE_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "sandstone_pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":fortified_desert"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":sandstone_house"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":desert"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":desert_double"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        DARKOAK_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "dark_oak_pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_darkoak"), 3),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":medieval_spruce_double"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        TERRACOTTA_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "terracotta_pool"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":terracotta1"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":terracotta2"), 2),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":terracotta_double"), 1)
        ), StructureTemplatePool.Projection.RIGID));
        COMPANIONS_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(HumanCompanions.MOD_ID,
                "companions"),
                new ResourceLocation("empty"), ImmutableList.of(
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":companions/knight"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":companions/archer"), 1),
                Pair.of(StructurePoolElement.legacy(HumanCompanions.MOD_ID + ":companions/arbalist"), 1)
        ), StructureTemplatePool.Projection.RIGID));
    }
    public static ConfiguredStructureFeature<?, ?> Configured_Oak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(OAK_POOL, 1), HAS_OAK_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Oak_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(OAK_BIRCH_POOL, 1), HAS_OAK_BIRCH_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Birch_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(BIRCH_POOL, 1), HAS_BIRCH_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Acacia_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(ACACIA_POOL, 1), HAS_ACACIA_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Sandstone_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(SANDSTONE_POOL, 1), HAS_SANDSTONE_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Spruce_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(SPRUCE_POOL, 1), HAS_SPRUCE_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_DarkOak_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(DARKOAK_POOL, 1), HAS_DARKOAK_HOUSE, true);
    public static ConfiguredStructureFeature<?, ?> Configured_Terracotta_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(TERRACOTTA_POOL, 1), HAS_TERRACOTTA_HOUSE, true);

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "oak_house"),
                Configured_Oak_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "oak_birch_house"),
                Configured_Oak_Birch_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "birch_house"),
                Configured_Birch_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "acacia_house"),
                Configured_Acacia_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "sandstone_house"),
                Configured_Sandstone_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "spruce_house"),
                Configured_Spruce_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "darkoak_house"),
                Configured_DarkOak_House);
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "terracotta_house"),
                Configured_Terracotta_House);
    }
}
