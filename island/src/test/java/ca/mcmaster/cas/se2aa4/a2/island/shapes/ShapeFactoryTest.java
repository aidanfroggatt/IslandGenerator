package ca.mcmaster.cas.se2aa4.a2.island.shapes;

import ca.mcmaster.cas.se2aa4.a2.island.RandomWrapper;
import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import org.junit.jupiter.api.Test;

class ShapeFactoryTest {

    @Test
    void createCricle() {
        Buildable buildable = ShapeFactory.create(new Configuration(new String[]{"--shape", "circle"}));
        assert buildable instanceof Circle;
    }

    @Test
    void createOval() {
        Buildable buildable = ShapeFactory.create(new Configuration(new String[]{"--shape", "oval"}));
        assert buildable instanceof Oval;
    }

    @Test
    void createIrregular() {
        Buildable buildable = ShapeFactory.create(new Configuration(new String[]{"--shape", "irregular"}));
        assert buildable instanceof Irregular;
    }
}