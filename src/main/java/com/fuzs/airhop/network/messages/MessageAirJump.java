package com.fuzs.airhop.network.messages;

import com.fuzs.airhop.handler.ConfigHandler;
import com.fuzs.airhop.helper.JumpHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

public class MessageAirJump extends MessageBase<MessageAirJump> {

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void handleClientSide(MessageAirJump message, EntityPlayer player) {
    }

    @Override
    public void handleServerSide(MessageAirJump message, EntityPlayer player) {

        WorldServer world = ((EntityPlayerMP) player).getServerWorld();
        world.addScheduledTask(() -> {

            if (JumpHelper.doJump(player, !ConfigHandler.invertElytra)) {
                JumpHelper.setFallDistance(player);
                if (ConfigHandler.summonCloud) {
                    world.spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY, player.posZ,
                            15, 0.25F, 0.0F, 0.25F, 0.01F);
                }
            }

        });

    }

}
