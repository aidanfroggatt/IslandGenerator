package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

public class PlateauEnricher extends Enricher {
    @Override
    public Mesh process(Mesh mesh) {

        int randomPlateauAltitude = random.nextInt(10) + 1;
        int currAltitude;

        for (Polygon polygon : mesh.getPolygons()) {
            currAltitude = polygon.isOcean() ? 0 : randomPlateauAltitude;
            polygon.setAltitude(currAltitude);
        }
        return mesh;
    }
}
