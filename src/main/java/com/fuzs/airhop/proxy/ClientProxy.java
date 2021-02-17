package com.fuzs.airhop.proxy;

import com.fuzs.airhop.client.handler.PerformJumpHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    @Override
    public void onInit() {

        super.onInit();
        MinecraftForge.EVENT_BUS.register(new PerformJumpHandler());
    }

    @Override
    public EntityPlayer getClientPlayer() {

        return Minecraft.getMinecraft().player;
    }

}
