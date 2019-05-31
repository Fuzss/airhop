package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.config.Config;

@SuppressWarnings("unused")
@Config(modid = AirHop.MODID)
public class ConfigHandler {

    @Config.Name("enchantment")
    @Config.RequiresMcRestart
    public static EnchantmentConfig enchantmentConfig = new EnchantmentConfig();

    public static class EnchantmentConfig {

        @Config.Name("Enchantment Rarity")
        @Config.Comment("Rarity of this enchantment.")
        public Enchantment.Rarity rarity = Enchantment.Rarity.RARE;

        @Config.Name("Enchantment Type")
        @Config.Comment("Defines the piece of armour this enchantment can be applied to. If \"ALL\" is used, the levels on all armour pieces will be combined.")
        public EnumArmourType type = EnumArmourType.LEGGINGS;

        @Config.Name("Maximum Level")
        @Config.Comment("Maximum level for this enchantment. Each level provides one additional air hop.")
        @Config.RangeInt(min = 0)
        public int maxLevel = 3;

        @Config.Name("Treasure Enchantment")
        @Config.Comment("Makes the enchantment unobtainable from enchanting tables. It is only available on items from loot chests, fishing and villager trading.")
        public boolean treasureEnchantment = true;

    }

    @Config.Name("Reset Fall Distance")
    @Config.Comment("Reset the fall distance on every air hop. Otherwise each air hop only decreases it by the default jump height.")
    public static boolean resetFallDistance = false;

    @Config.Name("Prioritise Over Elytra")
    @Config.Comment("When wearing an elytra; don't use it when there are air hops left. Sneaking inverts this behaviour in-game.")
    public static boolean invertElytra = false;

    @Config.Name("Summon Cloud Puff")
    @Config.Comment("Summon a small particle cloud at every position the player air hops from.")
    public static boolean summonCloud = true;

    @Config.Name("Disable On Hungry")
    @Config.Comment("Block the air hop enchantment from functioning when the player is too hungry.")
    public static boolean f1BlockOnHungry = false;

    @Config.Name("Food Threshold")
    @Config.Comment("Amount of food the player needs to surpass in case \"Disable On Hungry\" is enabled.")
    @Config.RangeInt(min = 0, max = 20)
    public static int foodThreshold = 6;

    @Config.Name("Hop Exhaustion")
    @Config.Comment("Exhaustion multiplier per air hop compared to normal jumps.")
    @Config.RangeDouble(min = 0)
    public static double hopExhaustion = 4.0;

    public enum EnumArmourType {

        ALL(EnumEnchantmentType.ARMOR, null),
        HELMET(EnumEnchantmentType.ARMOR_HEAD, EntityEquipmentSlot.HEAD),
        CHESTPLATE(EnumEnchantmentType.ARMOR_CHEST, EntityEquipmentSlot.CHEST),
        LEGGINGS(EnumEnchantmentType.ARMOR_LEGS, EntityEquipmentSlot.LEGS),
        BOOTS(EnumEnchantmentType.ARMOR_FEET, EntityEquipmentSlot.FEET);

        private EnumEnchantmentType enchantmentType;
        private EntityEquipmentSlot equipmentSlot;

        EnumArmourType(EnumEnchantmentType type, EntityEquipmentSlot slot) {
            this.enchantmentType = type;
            this.equipmentSlot = slot;
        }

        public EnumEnchantmentType getType() {
            return this.enchantmentType;
        }

        public EntityEquipmentSlot getSlot() {
            return equipmentSlot;
        }
    }

}
