package com.fuzs.airhop.network.message;

import com.fuzs.airhop.element.AirHopElement;
import com.fuzs.puzzleslib_ah.capability.CapabilityController;
import com.fuzs.puzzleslib_ah.network.message.Message;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;

public class SSyncAirHopsMessage extends Message {

    private byte airHops;

    public SSyncAirHopsMessage() {

    }

    public SSyncAirHopsMessage(byte airHops) {

        this.airHops = airHops;
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeByte(this.airHops);
    }

    @Override
    public void read(PacketBuffer buf) {

        this.airHops = buf.readByte();
    }

    @Override
    protected MessageProcessor createProcessor() {

        return new SyncAirHopsProcessor();
    }

    private class SyncAirHopsProcessor implements MessageProcessor {

        @Override
        public void accept(PlayerEntity playerEntity) {

            if (!playerEntity.onGround) {

                CapabilityController.getCapability(playerEntity, AirHopElement.AIR_HOPS_CAPABILITY)
                        .ifPresent(cap -> cap.setAirHops(SSyncAirHopsMessage.this.airHops));
            }
        }

    }

}
