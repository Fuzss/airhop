package com.fuzs.airhop.capability.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAirHopsCapability extends INBTSerializable<CompoundNBT> {

    int getAirHops();

    void setAirHops(int i);

    void resetAirHops();

    void addAirHop();

}
