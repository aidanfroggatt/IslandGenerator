package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude.AltitudeEnricher;

import java.util.HashMap;
import java.util.Map;

public class EnricherFactory {

    private static final Map<String, Class> bindings = new HashMap<>();

    static {
        bindings.put(Configuration.ALTITUDE_SHORT, AltitudeEnricher.class);
        bindings.put(Configuration.AQUIFER_SHORT, AquiferEnricher.class);
        bindings.put(Configuration.RIVER_SHORT, RiverEnricher.class);
        bindings.put(Configuration.SOIL_SHORT, SoilEnricher.class);
        bindings.put(Configuration.LAKES_SHORT,LakeEnricher.class);
        bindings.put(Configuration.CITIES_SHORT, CityEnricher.class);

    }

    public static void enrich(Configuration configuration, Mesh mesh) {
        // Getting all options and their values
        Map<String, String> options = configuration.export();

        // Iterating over all enricher options
        for(String key: bindings.keySet()) {

            // If enricher options are in options
            if(options.containsKey(key)) {
                try {
                    // Getting enricher holder
                    Class klass = bindings.get(key);
                    Enricher enricher  = (Enricher) klass.getDeclaredConstructor(String.class).newInstance(options.get(key));
                    enricher.addSeed(options.get(Configuration.SEED_SHORT));
                    enricher.process(mesh);
                }catch (UnsupportedOperationException e) {
                    throw e;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
