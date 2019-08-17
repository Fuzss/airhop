package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;

/**
 * Values are based on the frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    private static final String AIR_HOP_NAME = "air_hop";
    public static final int AIR_HOP_MAX_LEVEL = 3;

//    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]
//            {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    public AirHopEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_LEGS, slots);
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
