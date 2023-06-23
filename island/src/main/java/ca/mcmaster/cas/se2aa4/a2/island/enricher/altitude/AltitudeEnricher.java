package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AltitudeEnricher extends Enricher {

    private static final Map<String, Class> bindings = new HashMap<>();
    public final Enricher enricher;

    static {
        bindings.put("volcano", VolcanoEnricher.class);
        bindings.put("artic", ArticEnricher.class);
        bindings.put("hill", HillEnricher.class);
        bindings.put("plateau", PlateauEnricher.class);
    }

    public AltitudeEnricher(String type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Throwing error if altitude type is not supported
        if(!bindings.containsKey(type)) {
            throw new UnsupportedOperationException("Unsupported Altitude type "+type+". Supported Altitude types are: "+ Arrays.toString(bindings.keySet().toArray(new String[0])));
        }

        // Getting sub-type of altitude enricher
        Class klass = bindings.get(type);

        // Setting the type of enricher to be used
        this.enricher = (Enricher) klass.getDeclaredConstructor().newInstance();
    }

    @Override
    public Mesh process(Mesh mesh) {
        this.enricher.addSeed(this.seed);
        // Processing with sub-type of enricher
        return this.enricher.process(mesh);
    }
}
