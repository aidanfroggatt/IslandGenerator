package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.adt.SoilType;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SoilEnricher extends Enricher {
    //more water should be absorbed near the shore in drier soil profile,
    // wetter by the coast, drier near the center
    //water absorption should be dispersed more evenly but less amount per tile.

    private static final Map<String, SoilType> bindings = new HashMap<>();

    static {
        bindings.put("dry", SoilType.DRY);
        bindings.put("wet", SoilType.WET);
        bindings.put("moderate", SoilType.MODERATE);
    }

    private SoilType soilType;

    public SoilEnricher(String type) {
        // Throwing error if altitude type is not supported
        if (!bindings.containsKey(type)) {
            throw new UnsupportedOperationException("Unsupported Soil type " + type + ". Supported Soil types are: " + Arrays.toString(bindings.keySet().toArray(new String[0])));
        }
        soilType = bindings.get(type);
    }

    @Override
    public Mesh process(Mesh mesh) {
        int soilMultiFactor = getSoilMultiplicationFactor(this.soilType);
        for(Polygon polygon: mesh.getPolygons()){
            polygon.setHumidity(polygon.getHumidity() * soilMultiFactor);
        }
        return mesh;
    }

    private int getSoilMultiplicationFactor(SoilType soilType) {
        if (soilType == SoilType.DRY) {
            return 3;
        } else if (soilType == SoilType.MODERATE) {
            return 2;
        }
        return 1;
    }
}
