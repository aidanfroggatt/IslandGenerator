package ca.mcmaster.cas.se2aa4.a2.island.shapes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;
import java.util.List;
import java.util.Random;

public class Irregular extends Shape implements Buildable {

    // instance variables representing the radius and centroid coordinates within mesh (x and y)
    private double cx, cy, radius;
    private int numIrregularBreak;

    // method to verify if the vertexes (x and y positions) are within the oval shape
    // returns a boolean based on the seperation of the two divisions: x and y
    @Override
    public boolean isInsideShape(Vertex meshVertex) {
        return Util.getCircleVal(cx, cy, meshVertex) < radius;
    }


    // method to rebuild the mesh to a oval shape which re-initializes the radius and centroid properties
    // returns the newly formatted mesh
    @Override
    public Mesh build(Mesh aMesh) {
        numIrregularBreak = random.nextInt(100) + 40;
        List<Polygon> shpPolys = aMesh.getPolygons();
        this.radius = (Math.min(aMesh.getWidth(), aMesh.getHeight()) / 2);
        this.radius -= (this.radius / 4);

        this.cx = aMesh.getWidth() / 2;
        this.cy = aMesh.getHeight() / 2;

        // generates the circular shape for island
        applyTileStructure(aMesh, shpPolys, TileType.LAND, TileType.OCEAN);

        // reformats to irregular shape
        irregularize(shpPolys);

        aMesh.setPolygons(shpPolys);

        return aMesh;

    }


    // method to set the irregular shape format to all polygons within the mesh to generate irregular shape
    private void irregularize(List<Polygon> shapePolys) {
        int currIrregularBreaks = 0;
        for (Polygon polygon : shapePolys) {
            if (polygon.shouldBeBeach() && currIrregularBreaks < numIrregularBreak) {
                irregularize(polygon);
                currIrregularBreaks++;
            }
        }
    }


    // method to set the irregular shape of each distinct polygon within the mesh's polygon list
    private void irregularize(Polygon polygon) {
        if (random.nextBoolean()) {
            return;
        }
        for (Polygon neighbor : polygon.getNeighbors()) {
            if (!neighbor.isOcean()) {
                neighbor.setTileType(TileType.OCEAN);
                irregularize(polygon);
            }
        }
    }

}
