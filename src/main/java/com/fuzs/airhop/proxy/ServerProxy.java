package com.fuzs.airhop.proxy;

import com.fuzs.airhop.handler.CommonEventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

}
