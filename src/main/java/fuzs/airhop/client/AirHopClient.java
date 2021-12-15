package fuzs.airhop.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.client.handler.AirHopHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = AirHop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AirHopClient {
    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        registerHandlers();
    }

    private static void registerHandlers() {
        final AirHopHandler handler = new AirHopHandler();
        MinecraftForge.EVENT_BUS.addListener(handler::onPlayerTick);
    }
}
