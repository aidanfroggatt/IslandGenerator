package ca.mcmaster.cas.se2aa4.a2.island.tiles;

public class Colors {
    public static final String OCEAN_COLOR = "0,87,143";
    public static final String LAGOON_COLOR = "103,168,209";
    public static final String LAND_COLOR = "255,255,255";
    public static final String BEACH_COLOR = "252,255,202";

    public static String getColor(TileType tileType) {
        switch (tileType) {
            case BEACH:
                return BEACH_COLOR;
            case LAGOON:
                return LAGOON_COLOR;
            case LAKE:
            case OCEAN:
                return OCEAN_COLOR;
            default:
                return LAND_COLOR;
        }
    }
}
