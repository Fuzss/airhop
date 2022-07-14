package fuzs.airhop.init;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.capability.AirHopsCapabilityImpl;
import fuzs.puzzleslib.capability.ForgeCapabilityController;
import fuzs.puzzleslib.capability.data.CapabilityKey;
import fuzs.puzzleslib.capability.data.PlayerRespawnStrategy;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ForgeModRegistry {
    private static final ForgeCapabilityController CAPABILITIES = ForgeCapabilityController.of(AirHop.MOD_ID);
    public static final CapabilityKey<AirHopsCapability> AIR_HOPS_CAPABILITY = CAPABILITIES.registerPlayerCapability("air_hops", AirHopsCapability.class, player -> new AirHopsCapabilityImpl(), PlayerRespawnStrategy.NEVER, new CapabilityToken<AirHopsCapability>() {});

    public static void touch() {

    }
}
