package ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Biomes extends Enricher {

    private static final Map<String, Class> bindings = new HashMap<>();

    static {
        bindings.put("canada", CanadaEnricher.class);
        bindings.put("brazil", BrazilEnricher.class);
        bindings.put("egypt", EgyptEnricher.class);
    }

    public final Enricher enricher;

    public Biomes(String type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Throwing error if altitude type is not supported
        if (!bindings.containsKey(type)) {
            throw new UnsupportedOperationException("Unsupported Altitude type " + type + ". Supported Altitude types are: " + Arrays.toString(bindings.keySet().toArray(new String[0])));
        }

        // Getting sub-type of altitude enricher
        Class klass = bindings.get(type);

        // Setting the type of enricher to be used
        this.enricher = (Enricher) klass.getDeclaredConstructor().newInstance();
    }

    @Override
    public Mesh process(Mesh mesh) {
        // Processing with sub-type of enricher
        return this.enricher.process(mesh);
    }
}
