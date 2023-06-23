package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;

public class ArticEnricher extends Enricher {

    @Override
    public Mesh process(Mesh mesh) {
        int randomAltitude = random.nextInt(mesh.getPolygons().size());

        for (Polygon polygon : mesh.getPolygons()) {
            polygon.setAltitude(randomAltitude);
        }
        return mesh;
    }
}
