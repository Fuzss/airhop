package fuzs.airhop;

import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.network.client.message.C2SAirHopMessage;
import fuzs.airhop.network.message.S2CSyncAirHopsMessage;
import fuzs.puzzleslib.config.AbstractConfig;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.core.CoreServices;
import fuzs.puzzleslib.network.MessageDirection;
import fuzs.puzzleslib.network.NetworkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirHop {
    public static final String MOD_ID = "airhop";
    public static final String MOD_NAME = "Air Hop";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = CoreServices.FACTORIES.network(MOD_ID);
    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<AbstractConfig, ServerConfig> CONFIG = CoreServices.FACTORIES.serverConfig(() -> new ServerConfig());

    public static void onConstructMod() {
        CONFIG.loadConfigs(MOD_ID);
        ModRegistry.touch();
        registerMessages();
    }

    private static void registerMessages() {
        NETWORK.register(S2CSyncAirHopsMessage.class, S2CSyncAirHopsMessage::new, MessageDirection.TO_CLIENT);
        NETWORK.register(C2SAirHopMessage.class, C2SAirHopMessage::new, MessageDirection.TO_SERVER);
    }
}
