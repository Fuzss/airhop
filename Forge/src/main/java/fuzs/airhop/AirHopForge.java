package fuzs.airhop;

import fuzs.airhop.api.event.v1.PlayerTickEvents;
import fuzs.airhop.api.event.v1.entity.living.LivingFallCallback;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.capability.v2.ForgeCapabilityHelper;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.event.v1.core.ForgeEventInvokerRegistry;
import fuzs.puzzleslib.api.event.v1.data.MutableFloat;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(AirHop.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirHopForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        registerEvents();
        ModConstructor.construct(AirHop.MOD_ID, AirHop::new);
        registerCapabilities();
    }

    private static void registerCapabilities() {
        ForgeCapabilityHelper.setCapabilityToken(ModRegistry.AIR_HOPS_CAPABILITY, new CapabilityToken<AirHopsCapability>() {});
    }

    private static void registerEvents() {
        ForgeEventInvokerRegistry.INSTANCE.register(PlayerTickEvents.Start.class, TickEvent.PlayerTickEvent.class, (PlayerTickEvents.Start callback, TickEvent.PlayerTickEvent evt) -> {
            if (evt.phase != TickEvent.Phase.START) return;
            callback.onStartTick(evt.player);
        });
        ForgeEventInvokerRegistry.INSTANCE.register(PlayerTickEvents.End.class, TickEvent.PlayerTickEvent.class, (PlayerTickEvents.End callback, TickEvent.PlayerTickEvent evt) -> {
            if (evt.phase != TickEvent.Phase.END) return;
            callback.onEndTick(evt.player);
        });
        ForgeEventInvokerRegistry.INSTANCE.register(LivingFallCallback.class, LivingFallEvent.class, (LivingFallCallback callback, LivingFallEvent evt) -> {
            MutableFloat fallDistance = MutableFloat.fromEvent(evt::setDistance, evt::getDistance);
            MutableFloat damageMultiplier = MutableFloat.fromEvent(evt::setDamageMultiplier, evt::getDamageMultiplier);
            if (callback.onLivingFall(evt.getEntity(), fallDistance, damageMultiplier).isInterrupt()) {
                evt.setCanceled(true);
            }
        });
    }
}
