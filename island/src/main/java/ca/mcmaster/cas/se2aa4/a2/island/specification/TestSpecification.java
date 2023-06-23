package ca.mcmaster.cas.se2aa4.a2.island.specification;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

import java.util.List;

public class TestSpecification implements Buildable{

    @Override
    public Mesh build(Mesh inputMesh) {

        // Get converted Polygons and vertices
        List<Polygon> polygonList = inputMesh.getPolygons();
        List<Structs.Vertex> vertices = inputMesh.getVertices();

        // Creating radius for outer circle
        double circleRadius = Math.min(inputMesh.getWidth(), inputMesh.getHeight())/2;
        circleRadius -= circleRadius/4;


        // Processing polygons for outer circle for ocean and land
        process(polygonList, vertices, new double[]{inputMesh.getWidth(), inputMesh.getHeight(), circleRadius}, TileType.OCEAN, null);

        // Processing polygons for inner circle for lagoons and land
        process(polygonList, vertices, new double[]{inputMesh.getWidth(), inputMesh.getHeight(), circleRadius / 2}, null, TileType.LAND);

        // Processing polygons to update beaches
        process(polygonList);

        inputMesh.setPolygons(polygonList);

        // Returning new mesh
        return inputMesh;
    }

    private List<Polygon> process(List<Polygon> polygonList, List<Structs.Vertex> vertices, double [] dimensions, TileType outsideType, TileType insideType) {

        // Iterating over polygons
        // and setting tile type
        for(Polygon polygon: polygonList) {
            double circleVal = Util.getCircleVal(dimensions[0]/2, dimensions[1]/2,vertices.get(polygon.getPolygon().getCentroidIdx()));
            TileType tileType = dimensions[2] < circleVal ? outsideType : insideType;
            if(polygon.getTileType() != null || tileType == null) {
                continue;
            }
            polygon.setTileType(tileType);
        }
        return polygonList;
    }

    private List<Polygon> process(List<Polygon> polygonList) {

        // Setting polygons as beach if near ocean
        for(Polygon polygon: polygonList) {
            polygon.setBeachIfNearOcean();
        }
        return polygonList;
    }


}
