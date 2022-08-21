package fuzs.airhop;

import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.handler.PlayerSyncHandler;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.capability.ForgeCapabilityController;
import fuzs.puzzleslib.core.CoreServices;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(AirHop.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirHopForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        CoreServices.FACTORIES.modConstructor(AirHop.MOD_ID).accept(new AirHop());
        registerCapabilities();
        registerHandlers();
    }

    private static void registerCapabilities() {
        ForgeCapabilityController.setCapabilityToken(ModRegistry.AIR_HOPS_CAPABILITY, new CapabilityToken<AirHopsCapability>() {});
    }

    private static void registerHandlers() {
        final PlayerFallHandler playerFallHandler = new PlayerFallHandler();
        MinecraftForge.EVENT_BUS.addListener((final TickEvent.PlayerTickEvent evt) -> {
            // start is important, otherwise this runs before fall distance is processed
            if (evt.phase != TickEvent.Phase.START) return;
            playerFallHandler.onPlayerTick$start(evt.player);
        });
        MinecraftForge.EVENT_BUS.addListener((final LivingFallEvent evt) -> {
            evt.setDistance(playerFallHandler.onLivingFall(evt.getEntity(), evt.getDistance(), evt.getDamageMultiplier()));
        });
        final PlayerSyncHandler playerSyncHandler = new PlayerSyncHandler();
        MinecraftForge.EVENT_BUS.addListener((final EntityJoinLevelEvent evt) -> {
            playerSyncHandler.onEntityJoinWorld(evt.getEntity(), evt.getLevel());
        });
    }
}
