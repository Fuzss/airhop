package com.fuzs.airhop.handler;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.CapabilityDispatcher;
import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.messages.MessageSyncAirJump;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommonHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent evt) {

        if (evt.phase == TickEvent.Phase.END) {

            if (evt.player.onGround) {
                CapabilityHolder.getAirHopsCap(evt.player).resetAirHops();
            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) evt.getEntity();
            int i = CapabilityHolder.getAirHopsCap(player).getAirHops();

            if (i > 0) {

                NetworkHandler.sendTo(new MessageSyncAirJump(i), player);

            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> evt) {

        if (evt.getObject() instanceof EntityPlayer) {

            if (!evt.getObject().hasCapability(CapabilityHolder.airHopsCap, null)) {

                evt.addCapability(new ResourceLocation(AirHop.MODID, "air_hops"), new CapabilityDispatcher());

            }

        }

    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent evt) {

        if (evt.getModID().equals(AirHop.MODID)) {

            ConfigManager.sync(AirHop.MODID, Config.Type.INSTANCE);

        }

    }

}
