package com.github.justin.companions.core;

import com.github.justin.companions.Companions;
import com.github.justin.companions.entity.KnightEntity;
import com.github.justin.companions.entity.ArcherEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Companions.MOD_ID);

    public static final RegistryObject<EntityType<KnightEntity>> KnightEntity =
            ENTITIES.register("knight", () -> EntityType.Builder.of(KnightEntity::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(Companions.MOD_ID, "knight").toString()));

    public static final RegistryObject<EntityType<ArcherEntity>> ArcherEntity =
            ENTITIES.register("archer", () -> EntityType.Builder.of(ArcherEntity::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(Companions.MOD_ID, "archer").toString()));
}
