package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;
import java.util.Optional;

public class ThicknessProperty implements PropertyAccess<Integer> {
    @Override
    public Optional<Integer> extract(List<Property> props) {
        String value = new Reader(props).get("thickness");
        if (value == null)
            return Optional.empty();
        return Optional.of(Integer.parseInt(value));
    }
}
