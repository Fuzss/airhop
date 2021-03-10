package com.fuzs.airhop.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAirHopsCapability extends INBTSerializable<CompoundNBT> {

    byte getAirHops();

    void setAirHops(byte i);

    void resetAirHops();

    void addAirHop();

}
