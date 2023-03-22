package fuzs.airhop;

import fuzs.airhop.api.event.v1.FabricEvents;
import fuzs.airhop.api.event.v1.FabricLivingEvents;
import fuzs.airhop.api.event.v1.PlayerTickEvents;
import fuzs.airhop.api.event.v1.entity.living.LivingFallCallback;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.event.v1.core.FabricEventInvokerRegistry;
import net.fabricmc.api.ModInitializer;

public class AirHopFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        registerEvents();
        ModConstructor.construct(AirHop.MOD_ID, AirHop::new);
    }

    private static void registerEvents() {
        FabricEventInvokerRegistry.INSTANCE.register(PlayerTickEvents.Start.class, FabricEvents.PLAYER_TICK_START);
        FabricEventInvokerRegistry.INSTANCE.register(PlayerTickEvents.End.class, FabricEvents.PLAYER_TICK_END);
        FabricEventInvokerRegistry.INSTANCE.register(LivingFallCallback.class, FabricLivingEvents.LIVING_FALL);
    }
}
