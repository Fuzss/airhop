package com.fuzs.airhop.handler;

import com.fuzs.airhop.enchantment.AirHopEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@SuppressWarnings("unused")
public class RegistryHandler {

    public static final Enchantment AIR_HOP = new AirHopEnchantment(Enchantment.Rarity.RARE, EnchantmentType.ARMOR);

    @SubscribeEvent
    public void registerEnchantment(RegistryEvent.Register<Enchantment> evt) {

        evt.getRegistry().register(AIR_HOP);

    }

}
