package com.fuzs.airhop.capability.container;

import net.minecraft.nbt.NBTTagCompound;

public class AirHops implements IAirHops {

    private int airHops;

    @Override
    public int getAirHops() {

        return this.airHops;
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
    public void setAirHops(int airHops) {

        this.airHops = airHops;
    }

    @Override
    public NBTTagCompound serializeNBT() {

        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("AirHops", this.airHops);

        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound compound) {

        this.airHops = compound.getInteger("AirHops");
    }

}