package com.github.justin.humancompanions.core;

import com.github.justin.humancompanions.HumanCompanions;
import com.github.justin.humancompanions.container.CompanionContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ContainerInit {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS
            , HumanCompanions.MOD_ID);

    public static final RegistryObject<MenuType<CompanionContainer>> COMPANION_INVENTORY =
            CONTAINERS.register("companion_inventory", () -> new MenuType<>(CompanionContainer::new));

    private ContainerInit() {

    }
}
