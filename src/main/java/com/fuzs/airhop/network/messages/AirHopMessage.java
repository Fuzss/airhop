package com.fuzs.airhop.network.messages;

import com.fuzs.airhop.capability.CapabilityHolder;
import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.helper.JumpHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Particles;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AirHopMessage {

    private final AirHopMessageData[] data;

    public AirHopMessage(AirHopMessageData... data) {

        this.data = data;

    }

    public static void writePacketData(AirHopMessage message, PacketBuffer buf) {

        buf.writeVarInt(message.data.length);

        for (AirHopMessageData data : message.data) {

            buf.writeByte(data.getHops());

        }

    }

    public static AirHopMessage readPacketData(PacketBuffer buf) {

        int size = buf.readVarInt();
        AirHopMessageData[] data = new AirHopMessageData[size];

        for (int i = 0; i < size; i++) {

            int type = buf.readUnsignedByte();

            data[i] = new AirHopMessageData(type);

        }

        return new AirHopMessage(data);

    }

    public static void processPacket(final AirHopMessage message, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            for (AirHopMessageData data : message.data) {

                if (data.getHops() == 0) {

                    EntityPlayerMP player = ctx.get().getSender();

                    if (player != null && JumpHelper.doJump(player, !ConfigHandler.GENERAL_CONFIG.invertElytra.get())) {

                        if (ConfigHandler.GENERAL_CONFIG.summonCloud.get()) {
                            player.getServerWorld().spawnParticle(Particles.CLOUD, player.posX, player.posY, player.posZ, 15, 0.25F, 0.0F, 0.25F, 0.01F);
                        }

                    }

                } else {

                    EntityPlayerSP player = Minecraft.getInstance().player;

                    if (!player.onGround) {

                        player.getCapability(CapabilityHolder.airHopsCap).ifPresent(it -> it.setAirHops(data.getHops()));

                    }

                }

            }

        });

        ctx.get().setPacketHandled(true);

    }

    public static class AirHopMessageData {

        private final int hops;

        public AirHopMessageData(int hops) {

            this.hops = hops;

        }

        private int getHops() {
            return this.hops;
        }

    }

}
