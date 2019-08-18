package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.enchantment.EnchantmentAirHop;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AirHop.MODID)
public class RegistryHandler {

    private static final EntityEquipmentSlot[] ARMOR_SLOTS = new EntityEquipmentSlot[]
            {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    public static final Enchantment AIR_HOP_ENCH = new EnchantmentAirHop(ConfigHandler.enchantmentConfig.rarity, ConfigHandler.enchantmentConfig.type, ARMOR_SLOTS);

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onRegisterEnchantment(RegistryEvent.Register<Enchantment> evt) {
        evt.getRegistry().register(AIR_HOP_ENCH);
    }

}
