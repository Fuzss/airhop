package fuzs.airhop.client;

import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.client.handler.AirHopHandler;
import net.fabricmc.api.ClientModInitializer;

public class AirHopFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerHandlers();
    }

    private static void registerHandlers() {
        final AirHopHandler airHopHandler = new AirHopHandler();
        PlayerTickEvents.END_TICK.register(airHopHandler::onPlayerTick$end);
    }
}
