package com.fuzs.airhop.network.message;

import com.fuzs.airhop.common.util.PerformJumpHelper;
import com.fuzs.airhop.config.ConfigBuildHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fml.LogicalSide;

import javax.annotation.Nullable;

public class SimpleHopMessage implements IMessage {

    public static final PerformJumpHelper JUMP_HELPER = new PerformJumpHelper();

    public SimpleHopMessage() {
    }

    @Override
    public void writePacketData(final PacketBuffer buf) {
    }

    @SuppressWarnings("unchecked")
    @Override
    public SimpleHopMessage readPacketData(final PacketBuffer buf) {

        return this;
    }

    @Override
    public void processPacket(@Nullable final PlayerEntity player) {

        if (player instanceof ServerPlayerEntity && JUMP_HELPER.doJump(player, !ConfigBuildHandler.INVERT_ELYTRA.get())) {

            if (ConfigBuildHandler.SUMMON_CLOUD.get()) {

                ((ServerPlayerEntity) player).getServerWorld().spawnParticle(ParticleTypes.CLOUD, player.getPosX(), player.getPosY(), player.getPosZ(), 15, 0.25F, 0.0F, 0.25F, 0.01F);
            }
        }
    }

    @Override
    public LogicalSide getExecutionSide() {

        return LogicalSide.SERVER;
    }

}
