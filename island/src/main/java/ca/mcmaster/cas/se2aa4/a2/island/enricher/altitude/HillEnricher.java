package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

public class HillEnricher extends Enricher {
    @Override
    public Mesh process(Mesh mesh) {
        int currAltitude;

        for (Polygon polygon : mesh.getPolygons()) {
            currAltitude = random.nextInt(mesh.getPolygons().size());
            polygon.setAltitude(currAltitude);
        }
        return mesh;
    }
}
