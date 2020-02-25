package com.fuzs.airhop;

import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.client.InitiateJumpHandler;
import com.fuzs.airhop.common.SyncCapabilityHandler;
import com.fuzs.airhop.config.ConfigBuildHandler;
import com.fuzs.airhop.enchantment.AirHopEnchantments;
import com.fuzs.airhop.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(AirHop.MODID)
public class AirHop {

    public static final String MODID = "airhop";
    public static final String NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(AirHop.NAME);

    public AirHop() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AirHopEnchantments::onRegistryEnchantment);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigBuildHandler.SPEC, MODID + ".toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(AirHopEnchantments::onModConfig);

    }

    private void commonSetup(final FMLCommonSetupEvent evt) {

        NetworkHandler.init();
        CapabilityHolder.register();
        MinecraftForge.EVENT_BUS.register(new SyncCapabilityHandler());

    }

    private void clientSetup(final FMLClientSetupEvent evt) {

        MinecraftForge.EVENT_BUS.register(new InitiateJumpHandler());

    }

}
