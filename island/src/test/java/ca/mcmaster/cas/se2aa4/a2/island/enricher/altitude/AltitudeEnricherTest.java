package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes.Biomes;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes.BrazilEnricher;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes.CanadaEnricher;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes.EgyptEnricher;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class AltitudeEnricherTest {

    @Test
    public void articEnricher() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AltitudeEnricher altitudeEnricher = new AltitudeEnricher("artic");
        assert altitudeEnricher.enricher instanceof ArticEnricher;
    }

    @Test
    public void hillEnricher() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AltitudeEnricher altitudeEnricher = new AltitudeEnricher("hill");
        assert altitudeEnricher.enricher instanceof HillEnricher;
    }

    @Test
    public void plateauEnricher() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AltitudeEnricher altitudeEnricher = new AltitudeEnricher("plateau");
        assert altitudeEnricher.enricher instanceof PlateauEnricher;
    }

    @Test
    public void volcanoEnricher() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AltitudeEnricher altitudeEnricher = new AltitudeEnricher("volcano");
        assert altitudeEnricher.enricher instanceof VolcanoEnricher;
    }

}