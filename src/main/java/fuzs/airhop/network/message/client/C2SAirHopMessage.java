package fuzs.airhop.network.message.client;

import fuzs.airhop.AirHop;
import fuzs.airhop.registry.ModRegistry;
import fuzs.airhop.world.entity.player.PlayerAirHopsTracker;
import fuzs.puzzleslib.network.message.Message;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class C2SAirHopMessage implements Message {
    @Override
    public void write(FriendlyByteBuf buf) {
        
    }

    @Override
    public void read(FriendlyByteBuf buf) {

    }

    @Override
    public AirHopHandler makeHandler() {
        return new AirHopHandler();
    }

    private static class AirHopHandler extends PacketHandler<C2SAirHopMessage> {
        @Override
        public void handle(C2SAirHopMessage packet, Player player, Object gameInstance) {
            player.jumpFromGround();
            player.fallDistance = 0.0F;
            ((PlayerAirHopsTracker) player).addAirHop();
            // added on top of normal jumping exhaustion (which is 0.1)
            final float airHopExhaustion = 3.0F;
            player.causeFoodExhaustion(player.isSprinting() ? 0.2F * airHopExhaustion : 0.05F * airHopExhaustion);
            this.damageBoots(player);
            this.playEffects(player);
        }

        private void damageBoots(Player player) {
            if (player.getRandom().nextDouble() < AirHop.CONFIG.server().damageChance) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
                stack.hurtAndBreak(1, player, thePlayer -> thePlayer.broadcastBreakEvent(EquipmentSlot.FEET));
            }
        }

        private void playEffects(Player player) {
            if (AirHop.CONFIG.server().summonCloud) {
                ((ServerPlayer) player).getLevel().sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 15, 0.25F, 0.0F, 0.25F, 0.01F);
            }
            if (AirHop.CONFIG.server().hopSound) {
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModRegistry.ENTITY_PLAYER_HOP_SOUND.get(), player.getSoundSource(), 1.0F, 0.6F + player.level.getRandom().nextFloat() * 0.8F);
            }
        }
    }
}
