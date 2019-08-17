package com.fuzs.airhop.handler;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig("general");

    public static class GeneralConfig {

        public final ForgeConfigSpec.BooleanValue resetFallDistance;
        public final ForgeConfigSpec.BooleanValue invertElytra;
        public final ForgeConfigSpec.BooleanValue summonCloud;
        public final ForgeConfigSpec.BooleanValue disableOnHungry;
        public final ForgeConfigSpec.IntValue foodThreshold;
        public final ForgeConfigSpec.DoubleValue hopExhaustion;

        private GeneralConfig(String name) {

            BUILDER.push(name);

            this.resetFallDistance = BUILDER.comment("Reset the fall distance on every air hop. Otherwise each air hop only decreases it by the default jump height.").define("Reset Fall Distance", false);
            this.invertElytra = BUILDER.comment("When wearing an elytra; don't use it when there are air hops left. Sneaking inverts this behaviour in-game.").define("Prioritise Over Elytra", false);
            this.summonCloud = BUILDER.comment("Summon a small particle cloud at every position the player air hops from.").define("Summon Cloud Puff", true);
            this.disableOnHungry = BUILDER.comment("Block the air hop enchantment from functioning when the player is too hungry.").define("Disable On Hungry", false);
            this.foodThreshold = BUILDER.comment("Amount of food the player needs to surpass in case \"Disable On Hungry\" is enabled.").defineInRange("Food Threshold", 6, 0, 20);
            this.hopExhaustion = BUILDER.comment("Exhaustion multiplier per air hop compared to normal jumps.").defineInRange("Hop Exhaustion", 4.0, 0.0, Double.MAX_VALUE);

            BUILDER.pop();

        }

    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
