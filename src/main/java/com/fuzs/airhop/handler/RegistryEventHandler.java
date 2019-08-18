package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.enchantment.EnchantmentAirHop;
import com.fuzs.airhop.capability.PlayerProperties;
import com.fuzs.airhop.capability.PropertiesDispatcher;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = AirHop.MODID)
public class RegistryEventHandler {

    private static final EntityEquipmentSlot[] ARMOR_SLOTS = new EntityEquipmentSlot[]
            {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    public static final Enchantment AIR_HOP = new EnchantmentAirHop(ConfigHandler.enchantmentConfig.rarity, ConfigHandler.enchantmentConfig.type, ARMOR_SLOTS);

    @SubscribeEvent
    public static void registerEnchantment(RegistryEvent.Register<Enchantment> evt) {
        evt.getRegistry().register(AIR_HOP);
    }

    @SubscribeEvent
    public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof EntityPlayer) {
            if (!event.getObject().hasCapability(PlayerProperties.PLAYER_AIRJUMPS, null)) {
                event.addCapability(new ResourceLocation(AirHop.MODID, "air_hops"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent evt) {
        if (evt.getModID().equals(AirHop.MODID)) {
            ConfigManager.sync(AirHop.MODID, Config.Type.INSTANCE);
        }
    }

}
