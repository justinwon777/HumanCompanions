package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.entity.Arbalist;
import com.github.justinwon777.humancompanions.entity.Archer;
import com.github.justinwon777.humancompanions.entity.Axeguard;
import com.github.justinwon777.humancompanions.entity.Knight;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HumanCompanions.MOD_ID);

    public static final RegistryObject<EntityType<Knight>> Knight =
            ENTITIES.register("knight", () -> EntityType.Builder.of(Knight::new, EntityClassification.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "knight").toString()));

    public static final RegistryObject<EntityType<Archer>> Archer =
            ENTITIES.register("archer", () -> EntityType.Builder.of(Archer::new, EntityClassification.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "archer").toString()));

    public static final RegistryObject<EntityType<Arbalist>> Arbalist =
            ENTITIES.register("arbalist", () -> EntityType.Builder.of(Arbalist::new, EntityClassification.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "arbalist").toString()));

    public static final RegistryObject<EntityType<Axeguard>> Axeguard =
            ENTITIES.register("axeguard", () -> EntityType.Builder.of(Axeguard::new, EntityClassification.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "axeguard").toString()));
}
