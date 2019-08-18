package com.fuzs.airhop.util;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

@SuppressWarnings("unused")
public enum EnumArmorType {

    ALL(EnumEnchantmentType.ARMOR, null),
    HELMET(EnumEnchantmentType.ARMOR_HEAD, EntityEquipmentSlot.HEAD),
    CHESTPLATE(EnumEnchantmentType.ARMOR_CHEST, EntityEquipmentSlot.CHEST),
    LEGGINGS(EnumEnchantmentType.ARMOR_LEGS, EntityEquipmentSlot.LEGS),
    BOOTS(EnumEnchantmentType.ARMOR_FEET, EntityEquipmentSlot.FEET);

    private EnumEnchantmentType enchantmentType;
    private EntityEquipmentSlot equipmentSlot;

    EnumArmorType(EnumEnchantmentType type, EntityEquipmentSlot slot) {
        this.enchantmentType = type;
        this.equipmentSlot = slot;
    }

    public EnumEnchantmentType getType() {
        return this.enchantmentType;
    }

    public EntityEquipmentSlot getSlot() {
        return this.equipmentSlot;
    }

}