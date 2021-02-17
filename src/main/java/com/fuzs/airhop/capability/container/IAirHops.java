package com.fuzs.airhop.capability.container;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAirHops extends INBTSerializable<NBTTagCompound> {

    int getAirHops();

    void resetAirHops();

    void addAirHop();

    void setAirHops(int airHops);

}
