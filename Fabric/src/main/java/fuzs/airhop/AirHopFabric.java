package fuzs.airhop;

import fuzs.airhop.api.event.LivingFallEvents;
import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;

public class AirHopFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CoreServices.FACTORIES.modConstructor(AirHop.MOD_ID).accept(new AirHop());
        registerHandlers();
    }

    private static void registerHandlers() {
        final PlayerFallHandler playerFallHandler = new PlayerFallHandler();
        PlayerTickEvents.START_TICK.register(playerFallHandler::onPlayerTick$start);
        LivingFallEvents.MODIFY_FALL_DISTANCE.register(playerFallHandler::onLivingFall);
    }
}
