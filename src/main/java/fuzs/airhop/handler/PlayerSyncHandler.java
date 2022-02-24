package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.network.message.S2CSyncAirHopsMessage;
import fuzs.airhop.registry.ModRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerSyncHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof ServerPlayer player) {
            // capability is not synced automatically, so a client could potentially re-log to regain all their air hops
            // this is not important anymore, since the amount of air hops is hardcoded to 3 (used to be configurable in the past),
            // but since we have the code might as well keep it
            player.getCapability(ModRegistry.AIR_HOPS_CAPABILITY).ifPresent(capability -> {
                if (capability.hasUsedAirHops()) {
                    AirHop.NETWORK.sendTo(new S2CSyncAirHopsMessage(capability.getAirHops()), player);
                }
            });
        }
    }
}
