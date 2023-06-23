package ca.mcmaster.cas.se2aa4.a2.island.shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

import java.util.List;

public class Circle extends Shape implements Buildable {

    private double cx, cy, radius;

    @Override
    public boolean isInsideShape(Vertex meshVertex) {
        return Util.getCircleVal(cx, cy, meshVertex) < radius;
    }


    @Override
    public Mesh build(Mesh aMesh) {
        List<Polygon> shapePolys = aMesh.getPolygons();
        this.radius = (Math.min(aMesh.getWidth(), aMesh.getHeight()) / 2);
        this.radius -= (this.radius / 4);

        this.cx = aMesh.getWidth() / 2;
        this.cy = aMesh.getHeight() / 2;

        applyTileStructure(aMesh, shapePolys, TileType.LAND, TileType.OCEAN);

        aMesh.setPolygons(shapePolys);

        return aMesh;

    }
}
