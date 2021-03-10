package com.fuzs.airhop.mixin.accessor;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface ILivingEntityAccessor {

    @Accessor
    boolean getIsJumping();

    @Accessor
    int getJumpTicks();

    @Accessor
    void setJumpTicks(int jumpTicks);

}
