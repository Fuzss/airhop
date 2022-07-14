package fuzs.airhop.mixin;

import fuzs.airhop.api.event.LivingFallEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    public void causeFallDamage$head(float distance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (!LivingFallEvents.LIVING_FALL.invoker().onLivingFall((LivingEntity) (Object) this, distance, damageMultiplier)) {
            callbackInfo.setReturnValue(false);
        }
    }

    @ModifyVariable(method = "causeFallDamage", at = @At(value = "HEAD", shift = At.Shift.AFTER), ordinal = 0)
    public float causeFallDamage$modifyDistance(float _distance, float distance, float damageMultiplier) {
        return LivingFallEvents.MODIFY_FALL_DISTANCE.invoker().modifyFallDistance((LivingEntity) (Object) this, distance, damageMultiplier);
    }

    @ModifyVariable(method = "causeFallDamage", at = @At(value = "HEAD", shift = At.Shift.AFTER), ordinal = 1)
    public float causeFallDamage$modifyDamage(float _damageMultiplier, float distance, float damageMultiplier) {
        return LivingFallEvents.MODIFY_DAMAGE_MULTIPLIER.invoker().modifyDamageMultiplier((LivingEntity) (Object) this, distance, damageMultiplier);
    }
}
