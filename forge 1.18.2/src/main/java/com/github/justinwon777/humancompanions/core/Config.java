package com.github.justinwon777.humancompanions.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

    public static ForgeConfigSpec.IntValue AVERAGE_HOUSE_SEPARATION;
    public static ForgeConfigSpec.BooleanValue FRIENDLY_FIRE;
    public static ForgeConfigSpec.BooleanValue FALL_DAMAGE;
    public static ForgeConfigSpec.BooleanValue SPAWN_ARMOR;
    public static ForgeConfigSpec.BooleanValue SPAWN_WEAPON;

    public static void register() {
        registerCommonConfig();
    }

    private static void registerCommonConfig() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Settings for world gen (Doesn't work in 1.18.2 and beyond. Use datapacks instead.)").push("World" +
                " Gen");
        AVERAGE_HOUSE_SEPARATION = COMMON_BUILDER
                .comment("Average chunk separation between companion houses")
                .defineInRange("averageHouseSeparation", 20, 11, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Companion config");
        FRIENDLY_FIRE = COMMON_BUILDER
                .comment("Toggles friendly fire between companions")
                .define("friendlyFire", false);
        FALL_DAMAGE = COMMON_BUILDER
                .comment("Toggles fall damage for companions")
                .define("fallDamage", true);
        SPAWN_ARMOR = COMMON_BUILDER
                .comment("Toggles whether companions spawn with armor")
                .define("spawnArmor", true);
        SPAWN_WEAPON = COMMON_BUILDER
                .comment("Toggles whether companions spawn with a weapon")
                .define("spawnWeapon", true);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }
}
