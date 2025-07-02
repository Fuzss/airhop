package fuzs.airhop;

import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.network.client.ServerboundAirHopMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.PayloadTypesContext;
import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingFallCallback;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerTickEvents;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirHop implements ModConstructor {
    public static final String MOD_ID = "airhop";
    public static final String MOD_NAME = "Air Hop";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    @Override
    public void onCommonSetup() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        LivingFallCallback.EVENT.register(PlayerFallHandler::onLivingFall);
        PlayerTickEvents.START.register(PlayerFallHandler::onStartPlayerTick);
    }

    @Override
    public void onRegisterPayloadTypes(PayloadTypesContext context) {
        context.playToServer(ServerboundAirHopMessage.class, ServerboundAirHopMessage.STREAM_CODEC);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocationHelper.fromNamespaceAndPath(MOD_ID, path);
    }
}
