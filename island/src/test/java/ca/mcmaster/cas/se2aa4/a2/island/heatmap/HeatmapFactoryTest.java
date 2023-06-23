package ca.mcmaster.cas.se2aa4.a2.island.heatmap;

import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeatmapFactoryTest {

    @Test
    void createElevationHeatmap() {
        Buildable buildable = HeatmapFactory.create(new Configuration(new String[]{"--heatmap", "altitude"}));
        assert buildable instanceof ElevationHeatmap;
    }

    @Test
    void createHumidityHeatmap() {
        Buildable buildable = HeatmapFactory.create(new Configuration(new String[]{"--heatmap", "humidity"}));
        assert buildable instanceof HumidityHeatmap;
    }

    @Test
    void createResourceProductionHeatmap() {
        Buildable buildable = HeatmapFactory.create(new Configuration(new String[]{"--heatmap", "resource"}));
        assert buildable instanceof ResourceProductionHeatmap;
    }
}