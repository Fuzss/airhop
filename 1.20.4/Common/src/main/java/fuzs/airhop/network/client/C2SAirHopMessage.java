package fuzs.airhop.network.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.network.v2.MessageV2;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class C2SAirHopMessage implements MessageV2<C2SAirHopMessage> {

    @Override
    public void write(FriendlyByteBuf buf) {
        
    }

    @Override
    public void read(FriendlyByteBuf buf) {

    }

    @Override
    public MessageHandler<C2SAirHopMessage> makeHandler() {
        return new MessageHandler<>() {

            @Override
            public void handle(C2SAirHopMessage packet, Player player, Object gameInstance) {
                player.jumpFromGround();
                player.fallDistance = 0.0F;
                ModRegistry.AIR_HOPS_CAPABILITY.get(player).addAirHop();
                // added on top of normal jumping exhaustion (which is 0.1)
                final float airHopExhaustion = 3.0F;
                player.causeFoodExhaustion(player.isSprinting() ? 0.2F * airHopExhaustion : 0.05F * airHopExhaustion);
                this.damageBoots(player);
                this.playEffects(player);
            }

            private void damageBoots(Player player) {
                if (player.getRandom().nextDouble() < AirHop.CONFIG.get(ServerConfig.class).damageChance) {
                    Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(ModRegistry.AIR_HOP_ENCHANTMENT.value(), player);
                    if (entry != null) {
                        entry.getValue().hurtAndBreak(1, player, thePlayer -> thePlayer.broadcastBreakEvent(entry.getKey()));
                    }
                }
            }

            private void playEffects(Player player) {
                if (AirHop.CONFIG.get(ServerConfig.class).summonCloud) {
                    ((ServerPlayer) player).serverLevel().sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 15, 0.25F, 0.0F, 0.25F, 0.01F);
                }
                if (AirHop.CONFIG.get(ServerConfig.class).hopSound) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModRegistry.ENTITY_PLAYER_HOP_SOUND.value(), player.getSoundSource(), 1.0F, 0.6F + player.getRandom().nextFloat() * 0.8F);
                }
            }
        };
    }
}
