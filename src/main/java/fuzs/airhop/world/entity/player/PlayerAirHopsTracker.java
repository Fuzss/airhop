package fuzs.airhop.world.entity.player;

public interface PlayerAirHopsTracker {
    int getAirHops();

    void setAirHops(int amount);

    default void resetAirHops() {
        this.setAirHops(0);
    }

    default void addAirHop() {
        this.setAirHops(this.getAirHops() + 1);
    }
}
