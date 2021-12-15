package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.network.message.S2CSyncAirHopsMessage;
import fuzs.airhop.world.entity.player.PlayerAirHopsTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;

public class PlayerFallHandler {
    public void onEntityJoinWorld(final EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof ServerPlayer player) {
            int airHops = ((PlayerAirHopsTracker) player).getAirHops();
            if (airHops > 0) {
                AirHop.NETWORK.sendTo(new S2CSyncAirHopsMessage(airHops), player);
            }
        }
    }

    public void onLivingFall(final LivingFallEvent evt) {
        if (evt.getEntityLiving() instanceof Player) {
            evt.setDistance(this.onGroundHit((Player) evt.getEntityLiving(), evt.getDistance()));
        }
    }

    public void onPlayerFall(final PlayerFlyableFallEvent evt) {
        evt.setDistance(this.onGroundHit(evt.getPlayer(), evt.getDistance()));
    }

    private float onGroundHit(Player player, float fallDistance) {
        int airHops = ((PlayerAirHopsTracker) player).getAirHops();
        ((PlayerAirHopsTracker) player).resetAirHops();
        if (!AirHop.CONFIG.server().fallDamage && airHops > 0) {
            return Math.max(0.0F, fallDistance - airHops * getJumpHeight(player));
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
