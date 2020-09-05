package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Values based on frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    public AirHopEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {

        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
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
        return ConfigBuildHandler.MAX_LEVEL.get();
    }

    @Override
    public boolean isTreasureEnchantment() {
        return ConfigBuildHandler.TREASURE_ENCHANTMENT.get();
    }

}
