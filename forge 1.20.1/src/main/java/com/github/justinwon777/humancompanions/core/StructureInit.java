package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.world.CompanionHouseStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureInit {
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE =
            DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, HumanCompanions.MOD_ID);

    public static final RegistryObject<StructureType<CompanionHouseStructure>> COMPANION_HOUSE =
            DEFERRED_REGISTRY_STRUCTURE.register("companion_house", () -> typeConvert(CompanionHouseStructure.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }
}
