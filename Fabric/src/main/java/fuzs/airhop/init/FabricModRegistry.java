package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.capability.AirHopsCapabilityImpl;
import fuzs.puzzleslib.capability.FabricCapabilityController;
import fuzs.puzzleslib.capability.data.CapabilityKey;
import fuzs.puzzleslib.capability.data.PlayerRespawnStrategy;

public class FabricModRegistry {
    private static final FabricCapabilityController CAPABILITIES = FabricCapabilityController.of(AirHop.MOD_ID);
    public static final CapabilityKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerPlayerCapability("air_hops", AirHopsCapability.class, player -> new AirHopsCapabilityImpl(), PlayerRespawnStrategy.NEVER);

    public static void touch() {

    }
}
