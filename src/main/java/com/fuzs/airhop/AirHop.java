package com.fuzs.airhop;

import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
@Mod(
        modid = AirHop.MODID,
        name = AirHop.NAME,
        version = "1.1.2",
        acceptedMinecraftVersions = "[1.12, 1.12.2]"
)
public class AirHop {

    public static final String MODID = "airhop";
    public static final String NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static final String CLIENT_PROXY = "com.fuzs." + MODID + ".proxy.ClientProxy";
    public static final String SERVER_PROXY = "com.fuzs." + MODID + ".proxy.CommonProxy";

    @SidedProxy(clientSide = AirHop.CLIENT_PROXY, serverSide = AirHop.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void onPreInit(final FMLPreInitializationEvent evt) {

        proxy.onPreInit();
    }

    @EventHandler
    public void onInit(final FMLInitializationEvent evt) {

        proxy.onInit();
        NetworkHandler.init();
    }

}
