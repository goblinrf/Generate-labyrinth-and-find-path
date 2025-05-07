package backend.academy.enums;

public enum SurfaceType {
    NORMAL(1),
    SWAMP(2),
    GOOD_TERRAIN(0);

    private final int cost;

    SurfaceType(int cost) {
        this.cost = cost;
    }
}
