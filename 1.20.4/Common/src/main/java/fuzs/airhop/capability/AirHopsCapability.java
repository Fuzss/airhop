package fuzs.airhop.capability;

import fuzs.puzzleslib.api.capability.v3.data.CapabilityComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class AirHopsCapability extends CapabilityComponent<Player> {
    private static final String TAG_AIR_HOPS = "air_hops";

    private int airHops;

    public int getAirHops() {
        return this.airHops;
    }

    public void setAirHops(int amount) {
        if (this.airHops != amount) {
            this.airHops = amount;
            this.setChanged();
        }
    }

    public void resetAirHops() {
        this.setAirHops(0);
    }

    public void addAirHop() {
        this.setAirHops(this.getAirHops() + 1);
    }

    @Override
    public void write(CompoundTag tag) {
        tag.putByte(TAG_AIR_HOPS, (byte) this.airHops);
    }

    @Override
    public void read(CompoundTag tag) {
        this.airHops = tag.getByte(TAG_AIR_HOPS);
    }
}
