package com.fuzs.airhop.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerProperties.java
 */
public class PlayerProperties {

    @CapabilityInject(PlayerAirJumps.class)
    public static Capability<PlayerAirJumps> PLAYER_AIRJUMPS;

    public static PlayerAirJumps getPlayerAirJumps(EntityPlayer player) {
        return player.getCapability(PLAYER_AIRJUMPS, null);
    }

}