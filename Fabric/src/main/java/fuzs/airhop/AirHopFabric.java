package fuzs.airhop;

import fuzs.airhop.api.event.LivingFallEvents;
import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.handler.PlayerSyncHandler;
import fuzs.airhop.init.FabricModRegistry;
import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class AirHopFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CoreServices.FACTORIES.modConstructor(AirHop.MOD_ID).accept(new AirHop());
        FabricModRegistry.touch();
        registerHandlers();
    }

    private static void registerHandlers() {
        final PlayerFallHandler playerFallHandler = new PlayerFallHandler();
        PlayerTickEvents.START_TICK.register(playerFallHandler::onPlayerTick$start);
        LivingFallEvents.MODIFY_FALL_DISTANCE.register(playerFallHandler::onLivingFall);
        final PlayerSyncHandler playerSyncHandler = new PlayerSyncHandler();
        ServerEntityEvents.ENTITY_LOAD.register(playerSyncHandler::onEntityJoinWorld);
    }
}
