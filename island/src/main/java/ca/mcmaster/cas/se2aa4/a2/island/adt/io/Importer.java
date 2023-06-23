package ca.mcmaster.cas.se2aa4.a2.island.adt.io;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Segment;

import java.util.ArrayList;
import java.util.List;

public class Importer {
    public Mesh importMesh(Structs.Mesh mesh) {
        List<Structs.Polygon> polygonList = mesh.getPolygonsList();
        List<Polygon> adtPolygons = new ArrayList<>();
        List<Segment> adtSegments = new ArrayList<>();

        for (Structs.Polygon polygon : polygonList) {
            adtPolygons.add(new Polygon(polygon, polygon.getPropertiesList()));
        }

        for (Structs.Segment segment : mesh.getSegmentsList()) {
            adtSegments.add(new Segment(segment));
        }

        for (int i = 0; i < polygonList.size(); i++) {
            Structs.Polygon polygon = polygonList.get(i);
            Polygon adtPolygon = adtPolygons.get(i);
            for (Integer neighborIdx : polygon.getNeighborIdxsList()) {
                adtPolygon.addNeighbor(adtPolygons.get(neighborIdx));
            }
        }

        return new Mesh(adtPolygons, adtSegments, mesh.getVerticesList());
    }
}
