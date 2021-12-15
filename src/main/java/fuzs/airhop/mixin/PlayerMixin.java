package fuzs.airhop.mixin;

import fuzs.airhop.world.entity.player.PlayerAirHopsTracker;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerAirHopsTracker {
    private static final EntityDataAccessor<Byte> DATA_AIR_HOPS = SynchedEntityData.defineId(Player.class, EntityDataSerializers.BYTE);

    public PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Override
    public int getAirHops() {
        return this.entityData.get(DATA_AIR_HOPS);
    }

    @Override
    public void setAirHops(int amount) {
        this.entityData.set(DATA_AIR_HOPS, (byte) amount);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(CallbackInfo callbackInfo) {
        this.entityData.define(DATA_AIR_HOPS, (byte) 0);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag tag, CallbackInfo callbackInfo) {
        this.setAirHops(tag.getByte("AirHops"));
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag tag, CallbackInfo callbackInfo) {
        tag.putByte("AirHops", (byte) this.getAirHops());
    }
}
