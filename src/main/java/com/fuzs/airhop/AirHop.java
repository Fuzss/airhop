package com.fuzs.airhop;

import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = AirHop.MODID,
        name = AirHop.NAME,
        version = AirHop.VERSION,
        acceptedMinecraftVersions = AirHop.RANGE,
        certificateFingerprint = AirHop.FINGERPRINT
)
public class AirHop
{
    public static final String MODID = "airhop";
    public static final String NAME = "Air Hop";
    public static final String VERSION = "@VERSION@";
    public static final String RANGE = "[1.12, 1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "com.fuzs.airhop.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.fuzs.airhop.proxy.ServerProxy";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final Logger LOGGER = LogManager.getLogger(AirHop.NAME);

    @SidedProxy(clientSide = AirHop.CLIENT_PROXY_CLASS, serverSide = AirHop.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        NetworkHandler.init();
    }

    @EventHandler
    public void fingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
