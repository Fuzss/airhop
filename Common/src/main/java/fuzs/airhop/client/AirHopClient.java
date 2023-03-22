package fuzs.airhop.client;

import fuzs.airhop.api.event.v1.PlayerTickEvents;
import fuzs.airhop.client.handler.AirHopHandler;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;

public class AirHopClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerHandlers();
    }

    private static void registerHandlers() {
        PlayerTickEvents.END.register(AirHopHandler::onPlayerTick$End);
    }
}
