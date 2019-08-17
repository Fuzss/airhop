package com.fuzs.airhop.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PropertiesDispatcher.java
 * Updated with help from Building Gadgets mod by Direwolf20 and Scaling Health mod by SilentChaos512
 */
public class CapabilityDispatcher implements ICapabilitySerializable<NBTTagCompound> {

    private final AirHopsCapability airHops = new AirHopsCapability();
    private final LazyOptional<AirHopsCapability> airHopsOptional = LazyOptional.of(() -> this.airHops);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
        if (cap == CapabilityHolder.airHopsCap) {
            return this.airHopsOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.airHops.write(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.airHops.read(nbt);
    }

}