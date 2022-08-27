package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.network.S2CSyncAirHopsMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class PlayerSyncHandler {

    public void onEntityJoinLevel(Entity entity, Level level) {
        if (entity instanceof ServerPlayer player) {
            // capability is not synced automatically, so a client could potentially re-log to regain all their air hops
            // this is not important anymore, since the amount of air hops is hardcoded to 3 (used to be configurable in the past),
            // but since we have the code might as well keep it
            ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player).ifPresent(capability -> {
                AirHop.LOGGER.info("PreUpdating air hops {}", capability.getAirHops());
                if (capability.hasUsedAirHops()) {
                    AirHop.LOGGER.info("Updating air hops {}", capability.getAirHops());
                    AirHop.NETWORK.sendTo(new S2CSyncAirHopsMessage(capability.toCompoundTag()), player);
                }
            });
        }
    }
}
