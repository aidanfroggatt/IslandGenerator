package ca.mcmaster.cas.se2aa4.a2.island.shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;
import java.util.List;
import java.util.Random;

public class Oval extends Shape implements Buildable {

    // instance variables representing the radius and centroid coordinates within mesh (x and y)
    private double rx, ry, cx, cy;

    // method to verify if the vertexes (x and y positions) are within the oval shape
    // returns a boolean based on the seperation of the two divisions: x and y
    @Override
    public boolean isInsideShape(Vertex meshVertex) {
        double firstDiv = Math.pow(cx - meshVertex.getX(), 2) / Math.pow(rx, 2);
        double secondDiv = Math.pow(cy - meshVertex.getY(), 2) / Math.pow(ry, 2);
        return firstDiv + secondDiv <= 1;
    }

    // method to rebuild the mesh to a oval shape which re-initializes the radius and centroid properties
    // returns the newly formatted mesh
    @Override
    public Mesh build(Mesh aMesh) {
        List<Polygon> shpPolys = aMesh.getPolygons();

        this.cx = aMesh.getWidth() / 2;
        this.cy = aMesh.getHeight() / 2;
        this.rx = (aMesh.getWidth() / 4);
        this.ry = (aMesh.getHeight() / 2)  -  (aMesh.getHeight() / 8);

        applyTileStructure(aMesh, shpPolys, TileType.LAND, TileType.OCEAN);

        aMesh.setPolygons(shpPolys);

        return aMesh;

    }
}