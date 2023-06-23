package ca.mcmaster.cas.se2aa4.a2.island.shapes;

import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {

    private static final Map<String, Class> bindings = new HashMap<>();

    static {
        bindings.put("circle", Circle.class);
        bindings.put("oval", Oval.class);
        bindings.put("irregular", Irregular.class);
    }

    public static Buildable create(Configuration configuration) {
        Map<String, String> options = configuration.export();
        // This code can be simplified with a switch case over the kind of mesh
        try {
            Class klass = bindings.get(options.get(Configuration.SHAPE_SHORT));
            Shape shape = (Shape) klass.getDeclaredConstructor().newInstance();
            shape.addSeed(options.get(Configuration.SEED_SHORT));
            return shape;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
