package com.fuzs.airhop.capability;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.storage.AirHopsCapability;
import com.fuzs.airhop.capability.storage.IAirHopsCapability;
import com.fuzs.airhop.capability.util.CapabilityDispatcher;
import com.fuzs.airhop.capability.util.CapabilityStorage;
import com.fuzs.airhop.enchantment.AirHopEnchantments;
import com.google.common.base.CaseFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AirHop.MODID)
public class CapabilityController {

    public static void register() {

        CapabilityManager.INSTANCE.register(IAirHopsCapability.class, new CapabilityStorage<>(), AirHopsCapability::new);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> evt) {

        if (evt.getObject() instanceof PlayerEntity) {

            evt.addCapability(AirHopEnchantments.locate(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, AirHopCapabilities.AIR_HOPS_NAME)), new CapabilityDispatcher<>(new AirHopsCapability(), AirHopCapabilities.AIR_HOPS));
        }
    }

}
