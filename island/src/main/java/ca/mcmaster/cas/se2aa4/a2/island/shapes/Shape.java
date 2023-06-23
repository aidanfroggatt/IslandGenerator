package ca.mcmaster.cas.se2aa4.a2.island.shapes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.RandomWrapper;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;

import java.util.List;
import java.util.Random;

public abstract class Shape extends RandomWrapper implements Buildable {

    public List<Polygon> applyTileStructure(Mesh islandMesh, List<Polygon> orgPolys, TileType inner, TileType outer) {
        for (Polygon poly : orgPolys) {
            TileType tile = isInsideShape(islandMesh.getVertices().get(poly.getPolygon().getCentroidIdx())) ? inner : outer;
            poly.setTileType(tile);
        }
        return orgPolys;
    }

    abstract boolean isInsideShape(Structs.Vertex meshVertex);
}
