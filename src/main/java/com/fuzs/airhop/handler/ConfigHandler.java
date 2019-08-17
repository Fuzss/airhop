package com.fuzs.airhop.handler;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final GeneralConfig GENERAL_CONFIG = new GeneralConfig("general");
//    public static final EnchantmentConfig ENCHANTMENT_CONFIG = new EnchantmentConfig("enchantment");

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

//    public static class EnchantmentConfig {
//
//        public final ForgeConfigSpec.EnumValue<Enchantment.Rarity> rarity;
//        public final ForgeConfigSpec.EnumValue<ArmorType> type;
//        public final ForgeConfigSpec.IntValue maxLevel;
//        public final ForgeConfigSpec.BooleanValue treasureEnchantment;
//
//        private EnchantmentConfig(String name) {
//
//            BUILDER.push(name);
//
//            this.rarity = BUILDER.comment(ConfigHelper.getEnumDescription("Rarity of this enchantment.", Enchantment.Rarity.values())).defineEnum("Enchantment Rarity", Enchantment.Rarity.RARE);
//            this.type = BUILDER.comment(ConfigHelper.getEnumDescription("Defines the piece of armor this enchantment can be applied to. If \"ALL\" is used, the levels on all armor pieces will be combined.", ArmorType.values())).defineEnum("Enchantment Type", ArmorType.LEGGINGS);
//            this.maxLevel = BUILDER.comment("Maximum level for this enchantment. Each level provides one additional air hop.").defineInRange("Maximum Level", 3, 0, Integer.MAX_VALUE);
//            this.treasureEnchantment = BUILDER.comment("Makes the enchantment unobtainable from enchanting tables. It is only available on items from loot chests, fishing and villager trading.").define("Treasure Enchantment", true);
//
//            BUILDER.pop();
//
//        }
//
//    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
