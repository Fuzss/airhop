package com.fuzs.airhop.capability;

import com.fuzs.airhop.capability.storage.AirHopsCapability;
import com.fuzs.airhop.capability.storage.IAirHopsCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class AirHopCapabilities {

    @CapabilityInject(IAirHopsCapability.class)
    public static final Capability<AirHopsCapability> AIR_HOPS = null;
    public static final String AIR_HOPS_NAME = "AirHops";

}
