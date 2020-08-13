package com.fuzs.airhop.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigBuildHandler {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // general
    public static final ForgeConfigSpec.BooleanValue INVERT_ELYTRA;
    public static final ForgeConfigSpec.BooleanValue SUMMON_CLOUD;
    public static final ForgeConfigSpec.BooleanValue DISABLE_ON_HUNGRY;
    public static final ForgeConfigSpec.IntValue FOOD_THRESHOLD;
    public static final ForgeConfigSpec.DoubleValue HOP_EXHAUSTION;
    // enchantment
    public static final ForgeConfigSpec.IntValue MAX_LEVEL;
    public static final ForgeConfigSpec.BooleanValue TREASURE_ENCHANTMENT;

    static {

        BUILDER.push("general");
        INVERT_ELYTRA = BUILDER.comment("When wearing an elytra; don't use it when there are air hops left. Sneaking inverts this behaviour in-game.").define("Prioritise Over Elytra", false);
        SUMMON_CLOUD = BUILDER.comment("Summon a small particle cloud at every position the player air hops from.").define("Summon Particle Cloud", true);
        DISABLE_ON_HUNGRY = BUILDER.comment("Block the air hop enchantment from functioning when the player is too hungry.").define("Disable On Hungry", false);
        FOOD_THRESHOLD = BUILDER.comment("Amount of food the player needs to surpass in case \"Disable On Hungry\" is enabled.").defineInRange("Food Threshold", 6, 0, 20);
        HOP_EXHAUSTION = BUILDER.comment("Exhaustion multiplier per air hop compared to normal jumps.").defineInRange("Hop Exhaustion", 4.0, 0.0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("enchantment");
        MAX_LEVEL = BUILDER.comment("Maximum level for this enchantment. Each level provides one additional air hop.").defineInRange("Maximum Level", 3, 0, Integer.MAX_VALUE);
        TREASURE_ENCHANTMENT = BUILDER.comment("Makes the enchantment unobtainable from enchanting tables. It is only available on items from loot chests, fishing and villager trading.").define("Treasure Enchantment", true);
        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
