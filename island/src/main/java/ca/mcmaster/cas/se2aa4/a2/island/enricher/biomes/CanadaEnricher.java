package ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes;

import ca.mcmaster.cas.se2aa4.a2.island.adt.BiomeType;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

public class CanadaEnricher extends Enricher implements Whitaker {

    @Override
    public Mesh process(Mesh mesh) {
        double[] minMaxHumidityAltitude = Util.getMinMaxHumidityAltitude(mesh.getPolygons());

        double humidityRatio, altitudeRatio;
        BiomeType biomeType;
        for (Polygon polygon : mesh.getPolygons()) {
            humidityRatio = (polygon.getHumidity() - minMaxHumidityAltitude[0]) / (minMaxHumidityAltitude[1] - minMaxHumidityAltitude[0]);
            altitudeRatio = (polygon.getAltitude() - minMaxHumidityAltitude[2]) / (minMaxHumidityAltitude[3] - minMaxHumidityAltitude[2]);
            if (polygon.isLand()) {
                biomeType = getBiomeType(humidityRatio, altitudeRatio);
                polygon.setColor(Util.getBiomeColor(biomeType));
                polygon.setBiomeType(biomeType);
                polygon.setResourceProduction(Util.processResourcesProduction(biomeType, humidityRatio, altitudeRatio));
            }
        }
        return mesh;
    }

    @Override
    public BiomeType getBiomeType(double humidity, double altitude) {
        // Arbitrary Canada Whitaker Diagram

        if (altitude > 0.5) {
            if (humidity > 0.5) {
                return BiomeType.TUNDRA;
            } else if (humidity > 0) {
                return BiomeType.TAIGA;
            }
            return BiomeType.TEMPERATE_RAIN_FOREST;
        } else if (altitude > 0) {
            if (humidity > 0.5) {
                return BiomeType.TEMPERATE_FOREST;
            }
            return BiomeType.TEMPERATE_RAIN_FOREST;
        }
        return BiomeType.TEMPERATE_DESERT;
    }
}