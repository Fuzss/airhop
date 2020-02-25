package com.fuzs.airhop.network;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.network.message.AirHopMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@SuppressWarnings("unused")
public class NetworkHandler {

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(AirHop.MODID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {

        int discriminator = 0;
        INSTANCE.registerMessage(discriminator, AirHopMessage.class, AirHopMessage::writePacketData, AirHopMessage::readPacketData, AirHopMessage::processPacket);

    }

    public static void sendToServer(Object message) {

        INSTANCE.sendToServer(message);

    }

    public static void sendTo(Object message, ServerPlayerEntity player) {

        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);

    }

    public static void sendToAll(Object message) {

        INSTANCE.send(PacketDistributor.ALL.noArg(), message);

    }

}