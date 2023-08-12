package fuzs.airhop;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class AirHopFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(AirHop.MOD_ID, AirHop::new);
    }
}
