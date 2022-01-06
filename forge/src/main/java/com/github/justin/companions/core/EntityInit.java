package com.github.justin.companions.core;

import com.github.justin.companions.Companions;
import com.github.justin.companions.entity.CompanionEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Companions.MOD_ID);

    public static final RegistryObject<EntityType<CompanionEntity>> CompanionEntity =
            ENTITIES.register("companion", () -> EntityType.Builder.of(CompanionEntity::new, MobCategory.MISC)
                    .sized(1f, 2f)
                    .build(new ResourceLocation(Companions.MOD_ID, "companion").toString()));
}
