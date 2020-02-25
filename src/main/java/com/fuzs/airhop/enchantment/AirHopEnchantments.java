package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class AirHopEnchantments {

    private static final String AIR_HOP_NAME = "air_hop";

    @ObjectHolder(AirHop.MODID + ":" + AIR_HOP_NAME)
    public static final Enchantment AIR_HOP = null;

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onRegistryEnchantment(RegistryEvent.Register<Enchantment> evt) {

        if (evt.getName().equals(ForgeRegistries.ENCHANTMENTS.getRegistryName())) {
            evt.getRegistry().register(new AirHopEnchantment(ConfigBuildHandler.ENCHANTMENT_CONFIG.rarity.get()).setRegistryName(buildLocation(AIR_HOP_NAME)));
        }

    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation buildLocation(String key) {
        return new ResourceLocation(AirHop.MODID, key);
    }

}
