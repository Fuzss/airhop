package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.CapabilityController;
import com.fuzs.airhop.config.ConfigHandler;
import com.fuzs.airhop.enchantment.EnchantmentAirHop;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.MessageSyncAirJump;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class CapabilityHandler {

    @ObjectHolder(AirHop.MODID + ":" + "air_hop")
    public static final Enchantment AIR_HOP_ENCHANTMENT = null;

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onRegisterEnchantment(final RegistryEvent.Register<Enchantment> evt) {

        EntityEquipmentSlot[] armorSlots = {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
        Enchantment entry = new EnchantmentAirHop(ConfigHandler.enchantmentConfig.rarity, ConfigHandler.enchantmentConfig.type, armorSlots);
        entry.setRegistryName(new ResourceLocation(AirHop.MODID, "air_hop")).setName("air_hop");
        evt.getRegistry().register(entry);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent evt) {

        if (evt.phase == TickEvent.Phase.END) {

            if (evt.player.onGround) {

                CapabilityController.getCapability(evt.player, CapabilityController.AIR_HOPS_CAPABILITY).resetAirHops();
            }
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) evt.getEntity();
            int airHops = CapabilityController.getCapability(player, CapabilityController.AIR_HOPS_CAPABILITY).getAirHops();
            if (airHops > 0) {

                NetworkHandler.sendTo(new MessageSyncAirJump(airHops), player);
            }
        }
    }

}
