package ca.mcmaster.cas.se2aa4.a2.island.tiles;

import org.junit.jupiter.api.Test;

class ColorsTest {

    @Test
    void getColor() {
        String color = Colors.getColor(TileType.LAND);
        assert color.equals(Colors.LAND_COLOR);

        color = Colors.getColor(TileType.OCEAN);
        assert color.equals(Colors.OCEAN_COLOR);

        color = Colors.getColor(TileType.LAKE);
        assert color.equals(Colors.OCEAN_COLOR);

        color = Colors.getColor(TileType.BEACH);
        assert color.equals(Colors.BEACH_COLOR);

        color = Colors.getColor(TileType.LAGOON);
        assert color.equals(Colors.LAGOON_COLOR);
    }
}