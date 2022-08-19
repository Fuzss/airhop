package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.puzzleslib_ah.registry.loadable.LoadableEnchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * values based on frost walker enchantment
 */
public class AirHopEnchantment extends LoadableEnchantment {

    public AirHopEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {

        super(rarityIn, EnchantmentType.ARMOR_LEGS, slots);
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

        // can't make this configurable as creative mod search is cached before config settings are loaded
        return 3;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    @Override
    protected boolean isEnabled() {

        return AirHop.AIR_HOP.isEnabled();
    }

}
