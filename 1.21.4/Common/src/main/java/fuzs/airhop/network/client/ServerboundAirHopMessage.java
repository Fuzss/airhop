package fuzs.airhop.network.client;

import com.google.common.base.Predicates;
import fuzs.airhop.AirHop;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.item.v2.ItemHelper;
import fuzs.puzzleslib.api.network.v3.ServerMessageListener;
import fuzs.puzzleslib.api.network.v3.ServerboundMessage;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Optional;

public record ServerboundAirHopMessage() implements ServerboundMessage<ServerboundAirHopMessage> {

    @Override
    public ServerMessageListener<ServerboundAirHopMessage> getHandler() {
        return new ServerMessageListener<>() {

            @Override
            public void handle(ServerboundAirHopMessage message, MinecraftServer server, ServerGamePacketListenerImpl handler, ServerPlayer player, ServerLevel level) {
                player.jumpFromGround();
                player.fallDistance = 0.0F;
                ModRegistry.AIR_HOPS_ATTACHMENT_TYPE.update(player, airHops -> ++airHops);
                // added on top of normal jumping exhaustion (which is 0.1)
                final float airHopExhaustion = 3.0F;
                player.causeFoodExhaustion(player.isSprinting() ? 0.2F * airHopExhaustion : 0.05F * airHopExhaustion);
                this.damageBoots(level, player);
                this.playEffects(level, player);
            }

            private void damageBoots(ServerLevel level, ServerPlayer player) {
                if (player.getRandom().nextDouble() < AirHop.CONFIG.get(ServerConfig.class).damageChance) {
                    Optional<EnchantedItemInUse> optional = EnchantmentHelper.getRandomItemWith(
                            ModRegistry.AIR_HOP_ENCHANTMENT_EFFECT_COMPONENT_TYPE.value(), player,
                            Predicates.alwaysTrue()
                    );
                    optional.ifPresent((EnchantedItemInUse enchantedItemInUse) -> ItemHelper.hurtAndBreak(
                            enchantedItemInUse.itemStack(), 1, level, player, enchantedItemInUse.onBreak()));
                }
            }

            private void playEffects(ServerLevel level, ServerPlayer player) {
                if (AirHop.CONFIG.get(ServerConfig.class).summonCloud) {
                    level.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 15, 0.25F,
                            0.0F, 0.25F, 0.01F
                    );
                }
                if (AirHop.CONFIG.get(ServerConfig.class).hopSound) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            ModRegistry.ENTITY_PLAYER_HOP_SOUND_EVENT.value(), player.getSoundSource(), 1.0F,
                            0.6F + player.getRandom().nextFloat() * 0.8F
                    );
                }
            }
        };
    }
}
