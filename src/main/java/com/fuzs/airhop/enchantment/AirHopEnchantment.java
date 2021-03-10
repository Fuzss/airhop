package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHopElements;
import com.fuzs.airhop.element.AirHopElement;
import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * values based on frost walker enchantment
 */
public class AirHopEnchantment extends Enchantment {

    public AirHopEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {

        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
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

        return AirHopElements.<AirHopElement>getAs(AirHopElements.AIR_HOP).maxLevel;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return AirHopElements.<AirHopElement>getAs(AirHopElements.AIR_HOP).treasureEnchantment;
    }

    @SuppressWarnings({"ConstantConditions", "NullableProblems"})
    @Override
    protected boolean canApplyTogether(Enchantment ench) {

        if (!AirHopElements.<AirHopElement>getAs(AirHopElements.AIR_HOP).incompatibility) {

            return super.canApplyTogether(ench);
        }

        if (ench instanceof DepthStriderEnchantment || ench instanceof FrostWalkerEnchantment) {

            return false;
        }

        // exclusive to some modding legacy mods
        String path = ench.getRegistryName().getPath();
        if (path.equals("dashing") || path.equals("leaping") || path.equals("stepping")) {

            return false;
        }

        return super.canApplyTogether(ench);
    }
}
