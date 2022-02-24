package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.registry.ModRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerFallHandler {
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent evt) {
        // start is important, otherwise this runs before fall distance is processed
        if (evt.phase != TickEvent.Phase.START) return;
        if (evt.player.isOnGround() && evt.player.getDeltaMovement().y() <= 0.0) {
            evt.player.getCapability(ModRegistry.AIR_HOPS_CAPABILITY).ifPresent(AirHopsCapability::resetAirHops);
        }
    }

    @SubscribeEvent
    public void onLivingFall(final LivingFallEvent evt) {
        if (evt.getEntityLiving() instanceof Player) {
            // survival mode
            evt.setDistance(this.onGroundHit((Player) evt.getEntityLiving(), evt.getDistance()));
        }
    }

    @SubscribeEvent
    public void onPlayerFall(final PlayerFlyableFallEvent evt) {
        // creative mode
        evt.setDistance(this.onGroundHit(evt.getPlayer(), evt.getDistance()));
    }

    private float onGroundHit(Player player, float fallDistance) {
        LazyOptional<AirHopsCapability> optional = player.getCapability(ModRegistry.AIR_HOPS_CAPABILITY);
        if (optional.isPresent()) {
            int airHops = optional.orElseThrow(IllegalStateException::new).getAirHops();
            if (!AirHop.CONFIG.server().fallDamage && airHops > 0) {
                return Math.max(0.0F, fallDistance - airHops * getJumpHeight(player));
            }
        }
        return fallDistance;
    }

    public static float getJumpHeight(Player player) {
        float jumpHeight = 1.25F;
        if (player.hasEffect(MobEffects.JUMP)) {
            jumpHeight += 0.6875F * (player.getEffect(MobEffects.JUMP).getAmplifier() + 1.0F);
        }
        return jumpHeight;
    }

}
