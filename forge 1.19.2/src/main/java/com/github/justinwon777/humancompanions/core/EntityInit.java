package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.entity.Arbalist;
import com.github.justinwon777.humancompanions.entity.Archer;
import com.github.justinwon777.humancompanions.entity.Axeguard;
import com.github.justinwon777.humancompanions.entity.Knight;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registry.ENTITY_TYPE_REGISTRY,
            HumanCompanions.MOD_ID);

    public static final RegistryObject<EntityType<Knight>> Knight =
            ENTITIES.register("knight", () -> EntityType.Builder.of(Knight::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "knight").toString()));

    public static final RegistryObject<EntityType<Archer>> Archer =
            ENTITIES.register("archer", () -> EntityType.Builder.of(Archer::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "archer").toString()));

    public static final RegistryObject<EntityType<Arbalist>> Arbalist =
            ENTITIES.register("arbalist", () -> EntityType.Builder.of(Arbalist::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "arbalist").toString()));

    public static final RegistryObject<EntityType<Axeguard>> Axeguard =
            ENTITIES.register("axeguard", () -> EntityType.Builder.of(Axeguard::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "axeguard").toString()));
}
