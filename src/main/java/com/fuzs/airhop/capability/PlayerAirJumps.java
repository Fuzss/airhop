package com.fuzs.airhop.capability;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerMana.java
 */
public class PlayerAirJumps {

    private int airJumps = 0;

    public PlayerAirJumps() {
    }

    public int getAirJumps() {
        return this.airJumps;
    }

    public void resetAirJumps() {
        this.airJumps = 0;
    }

    public void addAirJump() {
        this.airJumps++;
    }

    public void copyFrom(PlayerAirJumps source) {
        this.airJumps = source.airJumps;
    }

    void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("AirJumps", this.airJumps);
    }

    void loadNBTData(NBTTagCompound compound) {
        this.airJumps = compound.getInteger("AirJumps");
    }

}