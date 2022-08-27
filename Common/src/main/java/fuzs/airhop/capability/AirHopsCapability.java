package fuzs.airhop.capability;

import fuzs.puzzleslib.capability.data.SyncedCapabilityComponent;

public interface AirHopsCapability extends SyncedCapabilityComponent {

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
}
