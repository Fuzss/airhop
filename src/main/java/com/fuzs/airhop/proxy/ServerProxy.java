package com.fuzs.airhop.proxy;

import com.fuzs.airhop.handler.CommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class ServerProxy extends CommonProxy {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new CommonHandler());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

}
