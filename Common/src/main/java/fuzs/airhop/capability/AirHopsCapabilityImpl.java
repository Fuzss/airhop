package fuzs.airhop.capability;

import net.minecraft.nbt.CompoundTag;

public class AirHopsCapabilityImpl implements AirHopsCapability {
    private int airHops;

    @Override
    public int getAirHops() {
        return this.airHops;
    }

    @Override
    public void setAirHops(int amount) {
        this.airHops = amount;
    }

    @Override
    public void write(CompoundTag tag) {
        tag.putByte("AirHops", (byte) this.airHops);
    }

    @Override
    public void read(CompoundTag tag) {
        this.airHops = tag.getByte("AirHops");
    }
}
