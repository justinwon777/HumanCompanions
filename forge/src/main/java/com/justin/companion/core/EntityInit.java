package com.justin.companion.core;

import com.justin.companion.Companion;
import com.justin.companion.entity.NPC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityInit {

    private EntityInit() {}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Companion.MOD_ID);

    public static final RegistryObject<EntityType<com.justin.companion.entity.NPC>> NPC =
            ENTITIES.register("npc", () -> EntityType.Builder.of(NPC::new, MobCategory.AMBIENT)
                    .sized(1f, 1f)
                    .build(new ResourceLocation(Companion.MOD_ID, "npc").toString()));
}
