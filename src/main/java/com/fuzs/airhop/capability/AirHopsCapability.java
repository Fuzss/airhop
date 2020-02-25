package com.fuzs.airhop.capability;

import net.minecraft.nbt.CompoundNBT;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerMana.java
 * Updated with help from Building Gadgets mod by Direwolf20 and Scaling Health mod by SilentChaos512
 */
public class AirHopsCapability {

    private int airHops = 0;

    public AirHopsCapability() {
    }

    public int getAirHops() {
        return this.airHops;
    }

    public void setAirHops(int i) {
        this.airHops = i;
    }

    public void resetAirHops() {
        this.airHops = 0;
    }

    public void addAirHop() {
        this.airHops++;
    }

    public void write(CompoundNBT compound) {
        compound.putInt(CapabilityHolder.AIR_HOPS_CAP, this.airHops);
    }

    public void read(CompoundNBT compound) {
        this.airHops = compound.getInt(CapabilityHolder.AIR_HOPS_CAP);
    }

}