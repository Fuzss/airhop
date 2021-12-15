package fuzs.airhop;

import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.network.message.S2CSyncAirHopsMessage;
import fuzs.airhop.network.message.client.C2SAirHopMessage;
import fuzs.airhop.registry.ModRegistry;
import fuzs.puzzleslib.config.AbstractConfig;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.config.ConfigHolderImpl;
import fuzs.puzzleslib.network.MessageDirection;
import fuzs.puzzleslib.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AirHop.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirHop {
    public static final String MOD_ID = "airhop";
    public static final String MOD_NAME = "Air Hop";
    public static final Logger LOGGER = LogManager.getLogger(AirHop.MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.of(MOD_ID);
    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<AbstractConfig, ServerConfig> CONFIG = ConfigHolder.server(() -> new ServerConfig());

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ((ConfigHolderImpl<?, ?>) CONFIG).addConfigs(MOD_ID);
        ModRegistry.touch();
        registerHandlers();
        registerMessages();
    }

    private static void registerHandlers() {
        final PlayerFallHandler handler = new PlayerFallHandler();
        MinecraftForge.EVENT_BUS.addListener(handler::onEntityJoinWorld);
        MinecraftForge.EVENT_BUS.addListener(handler::onLivingFall);
        MinecraftForge.EVENT_BUS.addListener(handler::onPlayerFall);
    }

    private static void registerMessages() {
        NETWORK.register(S2CSyncAirHopsMessage.class, S2CSyncAirHopsMessage::new, MessageDirection.TO_CLIENT);
        NETWORK.register(C2SAirHopMessage.class, C2SAirHopMessage::new, MessageDirection.TO_CLIENT);
    }
}
