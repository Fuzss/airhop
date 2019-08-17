package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

/**
 * Values are based on the frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    private static final String AIR_HOP_NAME = "air_hop";
    private static final int AIR_HOP_MAX_LEVEL = 3;

    private static final EntityEquipmentSlot[] ARMOR_SLOTS = new EntityEquipmentSlot[]
            {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

    public AirHopEnchantment(Rarity rarityIn, EnumEnchantmentType type) {
        super(rarityIn, type, ARMOR_SLOTS);
        this.setRegistryName(new ResourceLocation(AirHop.MODID, AIR_HOP_NAME));
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
        return AIR_HOP_MAX_LEVEL;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

}
