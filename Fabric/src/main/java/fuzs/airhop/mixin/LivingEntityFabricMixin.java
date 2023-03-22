package fuzs.airhop.mixin;

import fuzs.airhop.api.event.v1.FabricLivingEvents;
import fuzs.puzzleslib.api.event.v1.data.DefaultedFloat;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityFabricMixin extends Entity {
    @Unique
    private DefaultedFloat airhop$fallDistance;
    @Unique
    private DefaultedFloat airhop$damageMultiplier;

    public LivingEntityFabricMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    public void causeFallDamage$0(float distance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> callback) {
        this.airhop$fallDistance = DefaultedFloat.fromValue(distance);
        this.airhop$damageMultiplier = DefaultedFloat.fromValue(damageMultiplier);
        if (FabricLivingEvents.LIVING_FALL.invoker().onLivingFall(LivingEntity.class.cast(this), this.airhop$fallDistance, this.airhop$damageMultiplier).isInterrupt()) {
            callback.setReturnValue(false);
        }
    }

    @ModifyVariable(method = "causeFallDamage", at = @At(value = "HEAD"), ordinal = 0)
    public float causeFallDamage$1(float fallDistance) {
        return this.airhop$fallDistance.getAsOptionalFloat().orElse(fallDistance);
    }

    @ModifyVariable(method = "causeFallDamage", at = @At(value = "HEAD"), ordinal = 1)
    public float causeFallDamage$2(float damageMultiplier) {
        return this.airhop$damageMultiplier.getAsOptionalFloat().orElse(damageMultiplier);
    }
}
