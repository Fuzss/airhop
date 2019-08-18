package com.fuzs.airhop.handler;

import com.fuzs.airhop.capability.PlayerProperties;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommonEventHandler {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent evt) {

        if (evt.phase == TickEvent.Phase.END) {

            if (evt.player.onGround) {
                PlayerProperties.getPlayerAirJumps(evt.player).resetAirJumps();
            }

        }

    }

}
