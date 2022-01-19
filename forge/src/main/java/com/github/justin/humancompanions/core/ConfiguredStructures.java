package com.github.justin.humancompanions.core;

import com.github.justin.humancompanions.HumanCompanions;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class ConfiguredStructures {
    public static ConfiguredStructureFeature<?, ?> Configured_Companion_House = StructureInit.COMPANION_HOUSE.get()
            .configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(HumanCompanions.MOD_ID, "configured_companion_house"),
                Configured_Companion_House);
    }
}
