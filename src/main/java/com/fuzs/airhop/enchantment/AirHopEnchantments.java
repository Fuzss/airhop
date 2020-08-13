package com.fuzs.airhop.enchantment;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = AirHop.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(AirHop.MODID)
public class AirHopEnchantments {

    @ObjectHolder("air_hop")
    public static final Enchantment AIR_HOP = null;

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onRegistryEnchantment(final RegistryEvent.Register<Enchantment> evt) {

        if (evt.getRegistry().equals(ForgeRegistries.ENCHANTMENTS)) {

            evt.getRegistry().register(new AirHopEnchantment(Enchantment.Rarity.RARE).setRegistryName(locate("air_hop")));
        }
    }

    public static ResourceLocation locate(String name) {

        return new ResourceLocation(AirHop.MODID, name);
    }

}
