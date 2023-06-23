package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class CityProperty implements PropertyAccess<Float>{
    @Override
    public Optional<Float> extract(List<Structs.Property> props) {
        String value = new Reader(props).get("city_size");
        if (value == null)
            return Optional.empty();
        return Optional.of(Float.parseFloat(value));
    }
}
