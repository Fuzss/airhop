package com.fuzs.airhop.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;

/**
 * Class modeled after McJty's Modding Tutorials series on YouTube, exact video is https://www.youtube.com/watch?v=3ThGMs5csnQ
 * Original source is available from https://github.com/McJty/YouTubeModdingTutorial/blob/master/src/main/java/mcjty/mymod/playermana/PlayerProperties.java
 * Updated with help from Building Gadgets mod by Direwolf20 and Scaling Health mod by SilentChaos512
 */
public class CapabilityHolder {

    public static final String AIR_HOPS_CAP_NAME = "air_hops";

    @CapabilityInject(AirHopsCapability.class)
    public static Capability<AirHopsCapability> AIR_HOPS_CAP;

    public static void register() {

        CapabilityManager.INSTANCE.register(AirHopsCapability.class, new Capability.IStorage<AirHopsCapability>() {

            @Override
            public void readNBT(Capability<AirHopsCapability> capability, AirHopsCapability instance, Direction side, INBT nbt) {
                instance.read((CompoundNBT) nbt);
            }

            @Nonnull
            @Override
            public INBT writeNBT(Capability<AirHopsCapability> capability, AirHopsCapability instance, Direction facing) {
                CompoundNBT nbt = new CompoundNBT();
                instance.write(nbt);
                return nbt;
            }

        }, AirHopsCapability::new);

    }

}