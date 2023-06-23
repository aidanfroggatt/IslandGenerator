package ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.BiomeType;

public interface Whitaker {
    public BiomeType getBiomeType(double humidity, double altitude);
}
