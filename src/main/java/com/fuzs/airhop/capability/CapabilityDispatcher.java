package com.fuzs.airhop.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PropertiesDispatcher.java
 */
public class CapabilityDispatcher implements ICapabilitySerializable<NBTTagCompound> {

    private AirHopsCapability airHops = new AirHopsCapability();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityHolder.airHopsCap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityHolder.airHopsCap) {
            return (T) this.airHops;
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.airHops.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.airHops.loadNBTData(nbt);
    }

}