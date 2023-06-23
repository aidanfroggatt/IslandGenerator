package ca.mcmaster.cas.se2aa4.a2.island.heatmap;

import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;

import java.util.HashMap;
import java.util.Map;

public class HeatmapFactory {

    private static final Map<String, Class> bindings = new HashMap<>();

    static {
        bindings.put("altitude", ElevationHeatmap.class);
        bindings.put("humidity", HumidityHeatmap.class);
        bindings.put("resource", ResourceProductionHeatmap.class);
    }

    public static Buildable create(Configuration configuration) {
        Map<String, String> options = configuration.export();

        try {
            Class klass = bindings.get(options.get(Configuration.HEATMAP_SHORT));
            return (Buildable) klass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
