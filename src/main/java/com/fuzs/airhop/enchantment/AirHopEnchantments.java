package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class AirHopEnchantments {

    private static final String AIR_HOP_NAME = "air_hop";

    @ObjectHolder(AirHop.MODID + ":" + AIR_HOP_NAME)
    public static final Enchantment AIR_HOP = null;

    public static void onRegistryEnchantment(final RegistryEvent.Register<Enchantment> evt) {

        if (evt.getName().equals(ForgeRegistries.ENCHANTMENTS.getRegistryName())) {
            evt.getRegistry().register(new AirHopEnchantment(ConfigBuildHandler.ENCHANTMENT_CONFIG.rarity.get()).setRegistryName(buildLocation(AIR_HOP_NAME)));
        }

    }

    @SuppressWarnings("ConstantConditions")
    public static void onModConfig(final ModConfig.ModConfigEvent evt) {

        if (evt.getConfig().getSpec() == ConfigBuildHandler.SPEC) {

            AirHopEnchantments.AIR_HOP.type = ConfigBuildHandler.ENCHANTMENT_CONFIG.type.get().getType();

        }
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation buildLocation(String key) {
        return new ResourceLocation(AirHop.MODID, key);
    }

}
