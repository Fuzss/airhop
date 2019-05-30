package com.fuzs.airhop.proxy;

import com.fuzs.airhop.handler.ClientEventHandler;
import com.fuzs.airhop.handler.CommonEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
