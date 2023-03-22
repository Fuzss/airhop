package fuzs.airhop.client;

import fuzs.airhop.AirHop;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class AirHopFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(AirHop.MOD_ID, AirHopClient::new);
    }
}
