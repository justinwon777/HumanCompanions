package com.github.justin.humancompanions.core;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.entity.HumanCompanionEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HumanCompanions.MOD_ID);

    public static final RegistryObject<EntityType<HumanCompanionEntity>> HumanCompanionEntity =
            ENTITIES.register("companion", () -> EntityType.Builder.of(HumanCompanionEntity::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "companion").toString()));
}
