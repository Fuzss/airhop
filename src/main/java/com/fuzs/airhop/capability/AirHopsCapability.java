package com.fuzs.airhop.capability;

import net.minecraft.nbt.CompoundNBT;

public class AirHopsCapability implements IAirHopsCapability {

    private byte airHops = 0;

    @Override
    public byte getAirHops() {

        return this.airHops;
    }

    @Override
    public void setAirHops(byte i) {

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
        nbt.putByte("AirHops", this.airHops);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

        this.airHops = nbt.getByte("AirHops");
    }

}