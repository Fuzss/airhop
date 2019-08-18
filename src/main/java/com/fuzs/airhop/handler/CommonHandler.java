package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.AirHopsCapability;
import com.fuzs.airhop.capability.CapabilityDispatcher;
import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.AirHopMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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

        if (evt.getEntity() instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) evt.getEntity();
            int i = player.getCapability(CapabilityHolder.airHopsCap).map(AirHopsCapability::getAirHops).orElse(0);

            if (i > 0) {

                NetworkHandler.sendTo(new AirHopMessage(new AirHopMessage.AirHopMessageData(i)), player);

            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> evt){

        if (evt.getObject() instanceof EntityPlayer) {
            if (!evt.getObject().getCapability(CapabilityHolder.airHopsCap).isPresent()) {
                evt.addCapability(new ResourceLocation(AirHop.MODID, CapabilityHolder.AIR_HOPS_CAP), new CapabilityDispatcher());
            }
        }

    }

}
