package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.world.CompanionHouseStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StructureInit {
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, HumanCompanions.MOD_ID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> COMPANION_HOUSE =
            DEFERRED_REGISTRY_STRUCTURE.register("companion_house",
                    () -> (new CompanionHouseStructure(JigsawConfiguration.CODEC)));
}
