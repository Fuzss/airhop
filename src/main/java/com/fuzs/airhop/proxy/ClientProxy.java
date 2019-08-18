package com.fuzs.airhop.proxy;

import com.fuzs.airhop.handler.ClientHandler;
import com.fuzs.airhop.handler.CommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new CommonHandler());
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

}
