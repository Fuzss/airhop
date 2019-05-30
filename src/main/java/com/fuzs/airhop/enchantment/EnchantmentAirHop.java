package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.handler.ConfigHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

/**
 * Values are based on the frost walker enchantment
 */
public class EnchantmentAirHop extends Enchantment {

    private static final String AIR_HOP = "air_hop";

    public EnchantmentAirHop(Rarity rarityIn, ConfigHandler.EnumArmourType type, EntityEquipmentSlot... slots) {
        super(rarityIn, type.getType(), slots);
        this.setRegistryName(new ResourceLocation(AirHop.MODID, AIR_HOP));
        this.setName(AIR_HOP);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel * 10;
    }

    /**
     * Returns the maximum value of enchantability needed on the enchantment level passed.
     */
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {
        return ConfigHandler.enchantmentConfig.maxLevel;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return ConfigHandler.enchantmentConfig.treasureEnchantment;
    }

}
