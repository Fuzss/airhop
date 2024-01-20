package fuzs.airhop.mixin.client.accessor;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Accessor("jumping")
    boolean airhop$getJumping();

    @Accessor("noJumpDelay")
    int airhop$getNoJumpDelay();

    @Accessor("noJumpDelay")
    void airhop$setNoJumpDelay(int noJumpDelay);
}
