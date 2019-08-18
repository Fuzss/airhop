package com.fuzs.airhop.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerProperties.java
 */
public class CapabilityHolder {

    @SuppressWarnings("WeakerAccess")
    public static final String AIR_HOPS_CAP = "air_hops";

    @CapabilityInject(AirHopsCapability.class)
    public static Capability<AirHopsCapability> airHopsCap;

    public static AirHopsCapability getAirHopsCap(EntityPlayer player) {
        return player.getCapability(airHopsCap, null);
    }

}