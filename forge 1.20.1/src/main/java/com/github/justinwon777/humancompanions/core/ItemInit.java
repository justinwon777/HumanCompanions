package com.github.justinwon777.humancompanions.core;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            HumanCompanions.MOD_ID);

    public static final RegistryObject<ForgeSpawnEggItem> Arbalist_Spawn_Egg = ITEMS.register("arbalist_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.Arbalist,0xE8AF5A, 0xFF0000,
                    new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> Archer_Spawn_Egg = ITEMS.register("archer_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.Archer,0xE8AF5A, 0x0000FF,
                    new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> Axeguard_Spawn_Egg = ITEMS.register("axeguard_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.Axeguard,0xE8AF5A, 0x00FF00,
                    new Item.Properties().stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> Knight_Spawn_Egg = ITEMS.register("knight_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.Knight,0xE8AF5A, 0xFFFF00,
                    new Item.Properties().stacksTo(64)));
}
