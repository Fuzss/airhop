package com.fuzs.airhop.common;

import com.fuzs.airhop.capability.AirHopCapabilities;
import com.fuzs.airhop.capability.storage.AirHopsCapability;
import com.fuzs.airhop.network.NetworkHandler;
import com.fuzs.airhop.network.message.SyncHopMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SyncCapabilityHandler {

    @SuppressWarnings({"unused", "ConstantConditions"})
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent evt) {

        if (evt.getEntity() instanceof ServerPlayerEntity) {

            ServerPlayerEntity player = (ServerPlayerEntity) evt.getEntity();
            int jumps = player.getCapability(AirHopCapabilities.AIR_HOPS).map(AirHopsCapability::getAirHops).orElse(0);
            if (jumps > 0) {

                NetworkHandler.getInstance().sendTo(new SyncHopMessage(jumps), player);
            }
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onLivingFall(final LivingFallEvent evt) {

        if (evt.getEntityLiving() instanceof PlayerEntity) {

            evt.setDistance(this.onGroundHit((PlayerEntity) evt.getEntityLiving(), evt.getDistance()));
        }
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onPlayerFall(final PlayerFlyableFallEvent evt) {

        evt.setDistance(this.onGroundHit(evt.getPlayer(), evt.getDistance()));
    }

    @SuppressWarnings("ConstantConditions")
    private float onGroundHit(PlayerEntity player, float fallDistance) {

        int hops = player.getCapability(AirHopCapabilities.AIR_HOPS).map(AirHopsCapability::getAirHops).orElse(0);
        player.getCapability(AirHopCapabilities.AIR_HOPS).ifPresent(AirHopsCapability::resetAirHops);
        if (hops > 0) {

            float f = 1.25F;
            if (player.isPotionActive(Effects.JUMP_BOOST)) {

                f += 0.6875F * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1.0F);
            }

            return Math.max(0.0F, fallDistance - hops * f);
        }

        return fallDistance;
    }

}
