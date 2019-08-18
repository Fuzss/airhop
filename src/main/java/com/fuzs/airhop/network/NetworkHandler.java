package com.fuzs.airhop.network;

import com.fuzs.airhop.AirHop;
import com.fuzs.airhop.network.messages.MessageAirJump;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings("unused")
public class NetworkHandler {

    private static SimpleNetworkWrapper INSTANCE;
    private static int discriminator;

    public static void init(){
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(AirHop.MODID);
        INSTANCE.registerMessage(MessageAirJump.class, MessageAirJump.class, nextDiscriminator(), Side.SERVER);
    }

    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point){
        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToAll(IMessage message){
        INSTANCE.sendToAll(message);
    }

    public static void sendToDimension(IMessage message, int dimensionId){
        INSTANCE.sendToDimension(message, dimensionId);
    }

    private static int nextDiscriminator() {
        return discriminator++;
    }

}