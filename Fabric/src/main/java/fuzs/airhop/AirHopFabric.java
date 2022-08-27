package fuzs.airhop;

import fuzs.airhop.api.event.LivingFallEvents;
import fuzs.airhop.api.event.PlayerTickEvents;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.handler.PlayerSyncHandler;
import fuzs.puzzleslib.core.CoreServices;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

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
        final PlayerSyncHandler playerSyncHandler = new PlayerSyncHandler();
        ServerEntityEvents.ENTITY_LOAD.register(playerSyncHandler::onEntityJoinLevel);
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((ServerPlayer player, ServerLevel origin, ServerLevel destination) -> {
            playerSyncHandler.onEntityJoinLevel(player, destination);
        });
    }
}
