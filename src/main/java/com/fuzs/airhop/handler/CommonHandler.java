package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.AirHopsCapability;
import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.capability.CapabilityDispatcher;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.AirHopMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent evt) {

        if (evt.phase == TickEvent.Phase.END) {

            if (evt.player.onGround) {
                evt.player.getCapability(CapabilityHolder.airHopsCap).ifPresent(AirHopsCapability::resetAirHops);
            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof ServerPlayerEntity) {

            ServerPlayerEntity player = (ServerPlayerEntity) evt.getEntity();
            int i = player.getCapability(CapabilityHolder.airHopsCap).map(AirHopsCapability::getAirHops).orElse(0);

            if (i > 0) {

                NetworkHandler.sendTo(new AirHopMessage(new AirHopMessage.AirHopMessageData(i)), player);

            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> evt){

        if (evt.getObject() instanceof PlayerEntity) {
            if (!evt.getObject().getCapability(CapabilityHolder.airHopsCap).isPresent()) {
                evt.addCapability(new ResourceLocation(AirHop.MODID, CapabilityHolder.AIR_HOPS_CAP), new CapabilityDispatcher());
            }
        }

    }

}
