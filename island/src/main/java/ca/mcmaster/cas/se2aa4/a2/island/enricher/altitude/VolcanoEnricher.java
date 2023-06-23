package ca.mcmaster.cas.se2aa4.a2.island.enricher.altitude;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

import java.util.List;

public class VolcanoEnricher extends Enricher {

    @Override
    public Mesh process(Mesh mesh) {

        // Center of mesh
        double midX = mesh.getWidth() / 2;
        double midY = mesh.getHeight() / 2;

        // Variables to get the polygon at the center of the mesh
        double distance = 0;
        int idx = 0;
        Structs.Vertex centroid;
        Structs.Polygon polygon;

        // Getting polygons and vertices
        List<Polygon> polygonList = mesh.getPolygons();
        List<Structs.Vertex> vertices = mesh.getVertices();

        // Iterating over each polygon and selecting the index of least distance one
        for (int i = 0; i < polygonList.size(); i++) {
            polygon = polygonList.get(i).getPolygon();
            centroid = vertices.get(polygon.getCentroidIdx());
            double currDistance = Util.getDistanceBetweenTwoPoints(centroid.getX(), centroid.getY(), midX, midY);
            if (currDistance < distance) {
                idx = i;
                distance = currDistance;
            }
        }

        // Getting the center polygon
        Polygon centerPolygon = polygonList.get(idx);
        int randomStartingAltitude = random.nextInt(polygonList.size()) + 255;
        processAltitude(centerPolygon, randomStartingAltitude);
        return mesh;
    }

    private void processAltitude(Polygon polygon, int altitude) {
        if (polygon.getAltitude() != null) {
            return;
        }
        polygon.setAltitude(altitude);
        for (Polygon neighbor : polygon.getNeighbors()) {
            processAltitude(neighbor, altitude - 1);
        }
    }
}
