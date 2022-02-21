package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import com.github.justinwon777.humancompanions.entity.HumanCompanionEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HumanCompanions.MOD_ID);

    public static final RegistryObject<EntityType<HumanCompanionEntity>> HumanCompanionEntity =
            ENTITIES.register("companion", () -> EntityType.Builder.of(HumanCompanionEntity::new,
                            EntityClassification.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(new ResourceLocation(HumanCompanions.MOD_ID, "companion").toString()));
}
