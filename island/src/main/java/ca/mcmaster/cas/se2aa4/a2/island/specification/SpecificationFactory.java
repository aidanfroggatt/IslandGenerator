package ca.mcmaster.cas.se2aa4.a2.island.specification;

import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;

import java.util.HashMap;
import java.util.Map;

public class SpecificationFactory {

    private static final Map<String, Class> bindings = new HashMap<>();

    static {
        bindings.put("lagoon", LagoonSpecification.class);
        bindings.put("test", TestSpecification.class);
    }
    public static Buildable create(Configuration configuration) {
        Map<String, String> options = configuration.export();
        // This code can be simplified with a switch case over the kind of mesh
        try {
            Class klass = bindings.get(options.get(Configuration.MODE_SHORT));
            return (Buildable) klass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("No mode provided!");
        }
        return null;
    }
}
