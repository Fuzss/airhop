package com.fuzs.airhop.capability;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.container.AirHops;
import com.fuzs.airhop.capability.container.IAirHops;
import com.fuzs.airhop.capability.core.CapabilityDispatcher;
import com.fuzs.airhop.capability.core.CapabilityStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityController {

    @CapabilityInject(IAirHops.class)
    public static final Capability<AirHops> AIR_HOPS_CAPABILITY = null;

    public CapabilityController() {

        CapabilityManager.INSTANCE.register(IAirHops.class, new CapabilityStorage<>(), AirHops::new);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onAttachCapabilities(final AttachCapabilitiesEvent<Entity> evt) {

        if (evt.getObject() instanceof EntityPlayer) {

            evt.addCapability(new ResourceLocation(AirHop.MODID, "air_hops"), new CapabilityDispatcher<>(new AirHops(), AIR_HOPS_CAPABILITY));
        }
    }

    public static <T> T getCapability(ICapabilityProvider provider, Capability<T> cap) {

        return provider.getCapability(cap, null);
    }

}
