package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.CityType;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import pathfinder.adt.Graph;

import java.util.*;

public class CityEnricher extends Enricher {
    int numOfCities;
    public CityEnricher(int numOfCities) {
        if (numOfCities < 0) {
            throw new UnsupportedOperationException("Unsupported number of cities " + numOfCities + ". Please make sure that the number of cities is greater than or equal to 0.");
        }
        this.numOfCities = numOfCities;
    }


    public CityEnricher(String numOfCities) {
        this(Integer.parseInt(numOfCities));
    }

    @Override
    public Mesh process(Mesh mesh) {
        List<Polygon> polygons = mesh.getPolygons();
        List<Integer> visitedTiles = new ArrayList<>();
        int numOfPolygons = polygons.size(), randPolygonIdx;
        Polygon currPolygon;

        for (int i = 0; i < numOfCities; i++) {
            randPolygonIdx = random.nextInt(numOfPolygons);
            currPolygon = polygons.get(randPolygonIdx);
            //can only place a city on land and prevent placing a city on the same tile twice
            if (!currPolygon.isLand() || visitedTiles.contains(randPolygonIdx)) {
                i--;
                continue;
            }
            visitedTiles.add(randPolygonIdx);

            //set the type of city
            if (i==0){currPolygon.setCityType(CityType.CITY);}
            else {currPolygon.setCityType(CityType.TOWN);}

            //add to list of cities

            //**can be removed** cities cannot be placed next to each other
            for (Polygon neighbor : currPolygon.getNeighbors()) {
                visitedTiles.add(neighbor.getPolygon().getCentroidIdx());
            }
        }
        return mesh;
    }
}