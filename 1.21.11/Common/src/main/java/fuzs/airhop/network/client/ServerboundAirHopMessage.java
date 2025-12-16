package fuzs.airhop.network.client;

import com.google.common.base.Predicates;
import fuzs.airhop.AirHop;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.item.v2.ItemHelper;
import fuzs.puzzleslib.api.network.v4.message.MessageListener;
import fuzs.puzzleslib.api.network.v4.message.play.ServerboundPlayMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Optional;

public record ServerboundAirHopMessage() implements ServerboundPlayMessage {
    public static final StreamCodec<ByteBuf, ServerboundAirHopMessage> STREAM_CODEC = StreamCodec.unit(new ServerboundAirHopMessage());

    @Override
    public MessageListener<Context> getListener() {
        return new MessageListener<Context>() {
            @Override
            public void accept(Context context) {
                ServerPlayer serverPlayer = context.player();
                serverPlayer.jumpFromGround();
                serverPlayer.fallDistance = 0.0F;
                ModRegistry.AIR_HOPS_ATTACHMENT_TYPE.apply(serverPlayer, (Byte airHops) -> ++airHops);
                // added on top of normal jumping exhaustion (which is 0.1)
                float airHopExhaustion = 3.0F;
                serverPlayer.causeFoodExhaustion(
                        serverPlayer.isSprinting() ? 0.2F * airHopExhaustion : 0.05F * airHopExhaustion);
                this.damageBoots(context.level(), serverPlayer);
                this.playEffects(context.level(), serverPlayer);
            }

            private void damageBoots(ServerLevel serverLevel, ServerPlayer serverPlayer) {
                if (serverPlayer.getRandom().nextDouble() < AirHop.CONFIG.get(ServerConfig.class).damageChance) {
                    Optional<EnchantedItemInUse> optional = EnchantmentHelper.getRandomItemWith(ModRegistry.AIR_HOP_ENCHANTMENT_EFFECT_COMPONENT_TYPE.value(),
                            serverPlayer,
                            Predicates.alwaysTrue());
                    optional.ifPresent((EnchantedItemInUse enchantedItemInUse) -> ItemHelper.hurtAndBreak(
                            enchantedItemInUse.itemStack(),
                            1,
                            serverLevel,
                            serverPlayer,
                            enchantedItemInUse.onBreak()));
                }
            }

            private void playEffects(ServerLevel serverLevel, ServerPlayer serverPlayer) {
                if (AirHop.CONFIG.get(ServerConfig.class).summonCloud) {
                    serverLevel.sendParticles(ParticleTypes.CLOUD,
                            serverPlayer.getX(),
                            serverPlayer.getY(),
                            serverPlayer.getZ(),
                            15,
                            0.25F,
                            0.0F,
                            0.25F,
                            0.01F);
                }

                if (AirHop.CONFIG.get(ServerConfig.class).hopSound) {
                    serverLevel.playSound(null,
                            serverPlayer.getX(),
                            serverPlayer.getY(),
                            serverPlayer.getZ(),
                            ModRegistry.ENTITY_PLAYER_HOP_SOUND_EVENT.value(),
                            serverPlayer.getSoundSource(),
                            1.0F,
                            0.6F + serverPlayer.getRandom().nextFloat() * 0.8F);
                }
            }
        };
    }
}
