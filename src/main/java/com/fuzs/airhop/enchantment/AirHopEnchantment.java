package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Values are based on the frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]
            {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    @SuppressWarnings("ConstantConditions")
    public AirHopEnchantment(Rarity rarityIn) {
        super(rarityIn, null, ARMOR_SLOTS);
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
        return ConfigBuildHandler.ENCHANTMENT_CONFIG.maxLevel.get();
    }

    @Override
    public boolean isTreasureEnchantment() {
        return ConfigBuildHandler.ENCHANTMENT_CONFIG.treasureEnchantment.get();
    }

}
