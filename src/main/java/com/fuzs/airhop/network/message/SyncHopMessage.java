package com.fuzs.airhop.network.message;

import com.fuzs.airhop.capability.AirHopCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;

import javax.annotation.Nullable;

public class SyncHopMessage implements IMessage {

    private int hops;

    public SyncHopMessage() {
    }

    public SyncHopMessage(int hops) {

        this.hops = hops;
    }

    @Override
    public void writePacketData(final PacketBuffer buf) {

        buf.writeByte(this.hops);
    }

    @SuppressWarnings("unchecked")
    @Override
    public SyncHopMessage readPacketData(final PacketBuffer buf) {

        this.hops = buf.readByte();
        return this;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void processPacket(@Nullable final PlayerEntity player) {

        if (player != null && !player.isOnGround()) {

            player.getCapability(AirHopCapabilities.AIR_HOPS).ifPresent(cap -> cap.setAirHops(this.hops));
        }
    }

    @Override
    public LogicalSide getExecutionSide() {

        return LogicalSide.CLIENT;
    }

}
