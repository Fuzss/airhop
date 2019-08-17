package com.fuzs.airhop;

import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.handler.ClientHandler;
import com.fuzs.airhop.handler.CommonHandler;
import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.handler.RegistryHandler;
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

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.SPEC, MODID + ".toml");
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryHandler());

    }

    private void commonSetup(final FMLCommonSetupEvent evt) {

        NetworkHandler.init();
        CapabilityHolder.register();
        MinecraftForge.EVENT_BUS.register(new CommonHandler());

    }

    private void clientSetup(final FMLClientSetupEvent evt) {

        MinecraftForge.EVENT_BUS.register(new ClientHandler());

    }

}
