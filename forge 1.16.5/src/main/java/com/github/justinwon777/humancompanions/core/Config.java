package com.github.justinwon777.humancompanions.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

    public static ForgeConfigSpec.IntValue AVERAGE_HOUSE_SEPARATION;
    public static ForgeConfigSpec.BooleanValue FRIENDLY_FIRE;
    public static ForgeConfigSpec.BooleanValue FALL_DAMAGE;

    public static void register() {
        registerCommonConfig();
    }

    private static void registerCommonConfig() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("Settings for world gen").push("World Gen");
        AVERAGE_HOUSE_SEPARATION = COMMON_BUILDER
                .comment("Average chunk separation between companion houses")
                .defineInRange("averageHouseSeparation", 20, 11, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Companion config");
        FRIENDLY_FIRE = COMMON_BUILDER
                .comment("Toggle friendly fire")
                .define("friendlyFire", false);
        FALL_DAMAGE = COMMON_BUILDER
                .comment("Toggle fall damage for companions")
                .define("fallDamage", true);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }
}
