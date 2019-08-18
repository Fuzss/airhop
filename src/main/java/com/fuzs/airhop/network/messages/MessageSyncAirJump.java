package com.fuzs.airhop.network.messages;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.CapabilityHolder;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncAirJump extends MessageBase<MessageSyncAirJump> {

    private int hops;

    @SuppressWarnings("unused")
    public MessageSyncAirJump() {
    }

    public MessageSyncAirJump(int i) {
        this.hops = i;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.hops = buf.readUnsignedByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(this.hops);
    }

    @Override
    public void handleClientSide(MessageSyncAirJump message, EntityPlayer player) {

        Minecraft.getMinecraft().addScheduledTask(() -> {

            EntityPlayer playerEntity = AirHop.proxy.getClientPlayer();
            int i = message.getHops();
            if (!playerEntity.onGround && i > 0) {
                CapabilityHolder.getAirHopsCap(playerEntity).setAirHops(i);
            }

        });

    }

    @Override
    public void handleServerSide(MessageSyncAirJump message, EntityPlayer player) {
    }

    @SideOnly(Side.CLIENT)
    private int getHops() {
        return this.hops;
    }

}
