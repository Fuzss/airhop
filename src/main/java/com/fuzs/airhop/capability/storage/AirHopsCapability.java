package com.fuzs.airhop.capability.storage;

import com.fuzs.airhop.capability.AirHopCapabilities;
import net.minecraft.nbt.CompoundNBT;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerMana.java
 * Updated with help from Building Gadgets mod by Direwolf20 and Scaling Health mod by SilentChaos512
 */
public class AirHopsCapability implements IAirHopsCapability {

    private int airHops = 0;

    @Override
    public int getAirHops() {

        return this.airHops;
    }

    @Override
    public void setAirHops(int i) {

        this.airHops = i;
    }

    @Override
    public void resetAirHops() {

        this.airHops = 0;
    }

    @Override
    public void addAirHop() {

        this.airHops++;
    }

    @Override
    public CompoundNBT serializeNBT() {

        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(AirHopCapabilities.AIR_HOPS_NAME, this.airHops);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

        this.airHops = nbt.getInt(AirHopCapabilities.AIR_HOPS_NAME);
    }

}