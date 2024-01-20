package fuzs.airhop.handler;

import fuzs.airhop.AirHop;
import fuzs.airhop.capability.AirHopsCapability;
import fuzs.airhop.config.ServerConfig;
import fuzs.airhop.init.ModRegistry;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import fuzs.puzzleslib.api.event.v1.data.MutableFloat;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PlayerFallHandler {

    public static EventResult onLivingFall(LivingEntity entity, MutableFloat fallDistance, MutableFloat damageMultiplier) {
        // fires for survival mode only, but this is fine since there is no fall damage in creative mode
        if (entity instanceof Player player) {
            AirHopsCapability capability = ModRegistry.AIR_HOPS_CAPABILITY.get(player);
            if (!AirHop.CONFIG.get(ServerConfig.class).fallDamage && capability.getAirHops() > 0) {
                fallDistance.mapFloat(distance -> Math.max(0.0F, distance - capability.getAirHops() * getJumpHeight(player)));
            }
        }
        return EventResult.PASS;
    }

    public static void onStartPlayerTick(Player player) {
        if (player.onGround()) {
            ModRegistry.AIR_HOPS_CAPABILITY.get(player).resetAirHops();
        }
    }

    public static float getJumpHeight(Player player) {
        float jumpHeight = 1.25F;
        if (player.hasEffect(MobEffects.JUMP)) {
            jumpHeight += 0.6875F * (player.getEffect(MobEffects.JUMP).getAmplifier() + 1.0F);
        }
        return jumpHeight;
    }
}
