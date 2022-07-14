package fuzs.airhop.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

public final class LivingFallEvents {
    public static final Event<AllowFallDamage> LIVING_FALL = EventFactory.createArrayBacked(AllowFallDamage.class, callbacks -> (LivingEntity entity, float distance, float damageMultiplier) -> {
        for (AllowFallDamage callback : callbacks) {
            if (!callback.onLivingFall(entity, distance, damageMultiplier)) {
                return false;
            }
        }
        return true;
    });

    public static final Event<ModifyFallDistance> MODIFY_FALL_DISTANCE = EventFactory.createArrayBacked(ModifyFallDistance.class, callbacks -> (LivingEntity entity, float distance, float damageMultiplier) -> {
        for (ModifyFallDistance callback : callbacks) {
            float newDistance = callback.modifyFallDistance(entity, distance, damageMultiplier);
            if (distance != newDistance) {
                return newDistance;
            }
        }
        return distance;
    });

    public static final Event<ModifyDamageMultiplier> MODIFY_DAMAGE_MULTIPLIER = EventFactory.createArrayBacked(ModifyDamageMultiplier.class, callbacks -> (LivingEntity entity, float distance, float damageMultiplier) -> {
        for (ModifyDamageMultiplier callback : callbacks) {
            float newDamageMultiplier = callback.modifyDamageMultiplier(entity, distance, damageMultiplier);
            if (damageMultiplier != newDamageMultiplier) {
                return newDamageMultiplier;
            }
        }
        return damageMultiplier;
    });

    @FunctionalInterface
    public interface AllowFallDamage {
        /**
         * called right at the beginning of {@link LivingEntity#causeFallDamage}, allows the method to be cancelled
         * @param entity the falling entity
         * @param distance         the distance the entity has fallen for calculting fall damage
         * @param damageMultiplier damage multiplier depending on the type of block <code>entity</code> is falling on
         * @return false to cancel fall damage
         */
        boolean onLivingFall(LivingEntity entity, float distance, float damageMultiplier);
    }

    @FunctionalInterface
    public interface ModifyFallDistance {
        /**
         * called right at the beginning of {@link LivingEntity#causeFallDamage}, allows modifying the fall distance if the method hasn't been cancelled via {@link AllowFallDamage}
         * @param entity the falling entity
         * @param distance         the distance the entity has fallen for calculting fall damage
         * @param damageMultiplier damage multiplier depending on the type of block <code>entity</code> is falling on
         * @return new fall distance
         */
        float modifyFallDistance(LivingEntity entity, float distance, float damageMultiplier);
    }

    @FunctionalInterface
    public interface ModifyDamageMultiplier {
        /**
         * called right at the beginning of {@link LivingEntity#causeFallDamage}, allows modifying the damage multiplier if the method hasn't been cancelled via {@link AllowFallDamage}
         * @param entity the falling entity
         * @param distance         the distance the entity has fallen for calculting fall damage
         * @param damageMultiplier damage multiplier depending on the type of block <code>entity</code> is falling on
         * @return new damage multiplier
         */
        float modifyDamageMultiplier(LivingEntity entity, float distance, float damageMultiplier);
    }
}
