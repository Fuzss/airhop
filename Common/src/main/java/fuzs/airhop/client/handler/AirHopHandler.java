package fuzs.airhop.client.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.handler.PlayerFallHandler;
import fuzs.airhop.init.ModRegistry;
import fuzs.airhop.mixin.accessor.LivingEntityAccessor;
import fuzs.airhop.network.client.message.C2SAirHopMessage;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Optional;

public class AirHopHandler {

    public void onPlayerTick$end(Player player) {
        if (((LivingEntityAccessor) player).getJumping() && ((LivingEntityAccessor) player).getNoJumpDelay() == 0 && this.attemptJump(player)) {
            // prevent accidental usage of air hops
            ((LivingEntityAccessor) player).setNoJumpDelay(10);
            // trigger jump on server
            AirHop.NETWORK.sendToServer(new C2SAirHopMessage());
        }
    }

    private boolean attemptJump(Player player) {
        if (this.canJump(player) && this.isSaturated(player)) {
            Optional<AirHopsCapability> optional = ModRegistry.AIR_HOPS_CAPABILITY.maybeGet(player);
            if (optional.isPresent()) {
                final AirHopsCapability capability = optional.get();
                if (capability.getAirHops() < EnchantmentHelper.getEnchantmentLevel(ModRegistry.AIR_HOP_ENCHANTMENT.get(), player)) {
                    player.jumpFromGround();
                    player.resetFallDistance();
                    capability.addAirHop();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canJump(Player player) {
        if (!player.isOnGround()) {
            if (!AirHop.CONFIG.server().fallingOnly || PlayerFallHandler.getJumpHeight(player) / 2.0F < player.fallDistance) {
                if (!(player.isPassenger() || player.getAbilities().flying || player.onClimbable())) {
                    return !(player.isInWater() || player.isInLava());
                }
            }
        }
        return false;
    }

    private boolean isSaturated(Player player) {
        // air hopping always works in creative mode
        return player.getAbilities().mayfly || !AirHop.CONFIG.server().disableOnHungry || player.getFoodData().getFoodLevel() > 6;
    }
}
