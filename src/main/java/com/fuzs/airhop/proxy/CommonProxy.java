package com.fuzs.airhop.proxy;

import com.fuzs.airhop.capability.CapabilityController;
import com.fuzs.airhop.handler.CapabilityHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public void onPreInit() {

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

    public void onInit() {

        MinecraftForge.EVENT_BUS.register(new CapabilityController());
    }

    public EntityPlayer getClientPlayer() {

        return null;
    }

}
