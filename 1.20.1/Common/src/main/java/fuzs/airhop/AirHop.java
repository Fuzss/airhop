package fuzs.airhop;

import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.network.client.C2SAirHopMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingFallCallback;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerTickEvents;
import fuzs.puzzleslib.api.network.v2.MessageDirection;
import fuzs.puzzleslib.api.network.v2.NetworkHandlerV2;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirHop implements ModConstructor {
    public static final String MOD_ID = "airhop";
    public static final String MOD_NAME = "Air Hop";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandlerV2 NETWORK = NetworkHandlerV2.build(MOD_ID);
    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
        registerMessages();
        registerHandlers();
    }

    private static void registerMessages() {
        NETWORK.register(C2SAirHopMessage.class, C2SAirHopMessage::new, MessageDirection.TO_SERVER);
    }

    private static void registerHandlers() {
        LivingFallCallback.EVENT.register(PlayerFallHandler::onLivingFall);
        PlayerTickEvents.START.register(PlayerFallHandler::onStartPlayerTick);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
