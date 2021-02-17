package com.fuzs.airhop.network.messages;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.capability.CapabilityController;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncAirJump extends MessageBase<MessageSyncAirJump> {

    private int airHops;

    @SuppressWarnings("unused")
    public MessageSyncAirJump() {
    }

    public MessageSyncAirJump(int i) {
        this.airHops = i;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.airHops = buf.readUnsignedByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(this.airHops);
    }

    @Override
    public void handleClientSide(MessageSyncAirJump message, EntityPlayer player) {

        Minecraft.getMinecraft().addScheduledTask(() -> {

            EntityPlayer playerEntity = AirHop.proxy.getClientPlayer();
            int i = message.getAirHops();
            if (!playerEntity.onGround && i > 0) {
                CapabilityController.getCapability(playerEntity, CapabilityController.AIR_HOPS_CAPABILITY).setAirHops(i);
            }

        });

    }

    @Override
    public void handleServerSide(MessageSyncAirJump message, EntityPlayer player) {
    }

    @SideOnly(Side.CLIENT)
    private int getAirHops() {
        return this.airHops;
    }

}
