package ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class BiomesTest {

    @Test
    public void brazilBiome() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Biomes biomes = new Biomes("brazil");
        assert biomes.enricher instanceof BrazilEnricher;
    }

    @Test
    public void canadaBiome() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Biomes biomes = new Biomes("canada");
        assert biomes.enricher instanceof CanadaEnricher;
    }

    @Test
    public void egyptBiome() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Biomes biomes = new Biomes("egypt");
        assert biomes.enricher instanceof EgyptEnricher;
    }

}