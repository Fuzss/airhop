package fuzs.airhop.client;

import fuzs.airhop.client.handler.AirHopHandler;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerTickEvents;

public class AirHopClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerHandlers();
    }

    private static void registerHandlers() {
        PlayerTickEvents.END.register(AirHopHandler::onEndPlayerTick);
    }
}
