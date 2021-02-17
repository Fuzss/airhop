package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.config.ConfigHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * values are based on frost walker enchantment
 */
public class EnchantmentAirHop extends Enchantment {

    public EnchantmentAirHop(Rarity rarityIn, ConfigHandler.EnumArmorType type, EntityEquipmentSlot... slots) {

        super(rarityIn, type.getType(), slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {

        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {

        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public int getMaxLevel() {

        return ConfigHandler.enchantmentConfig.maxLevel;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return ConfigHandler.enchantmentConfig.treasureEnchantment;
    }

}
