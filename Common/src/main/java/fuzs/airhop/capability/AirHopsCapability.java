package fuzs.airhop.capability;

import fuzs.puzzleslib.capability.data.CapabilityComponent;
import net.minecraft.nbt.CompoundTag;

public interface AirHopsCapability extends CapabilityComponent {
    int getAirHops();

    void setAirHops(int amount);

    default void resetAirHops() {
        this.setAirHops(0);
    }

    default void addAirHop() {
        this.setAirHops(this.getAirHops() + 1);
    }

    default boolean hasUsedAirHops() {
        return this.getAirHops() > 0;
    }

    default CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();
        this.write(tag);
        return tag;
    }
}
