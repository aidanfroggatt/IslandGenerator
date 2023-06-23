package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;

import java.util.List;
import java.util.Random;

public class LakeEnricher extends Enricher{
    int numOfLakes;

    public LakeEnricher(int numOfLakes) {
        if (numOfLakes < 0) {
            throw new UnsupportedOperationException("Unsupported number of lakes " + numOfLakes + ". Please make sure that the number of lakes is greater than or equal to 0.");
        }
        this.numOfLakes = numOfLakes;
    }


    public LakeEnricher(String numOfLakes) {
        this(Integer.parseInt(numOfLakes));
    }

    @Override
    public Mesh process(Mesh mesh) {
        List<Polygon> polygons = mesh.getPolygons();
        int numOfPolygons = polygons.size();
        Polygon currPolygon;
        int randPolygonIdx;
        for (int i = 0; i < numOfLakes; i++) {
            randPolygonIdx = random.nextInt(numOfPolygons);
            currPolygon = polygons.get(randPolygonIdx);
            if (!currPolygon.isLand()) {
                i--;
                continue;
            }
            currPolygon.setTileType(TileType.LAKE);
            for (Polygon neighbor : currPolygon.getNeighbors()) {
                neighbor.addHumidity(mesh.getSoilType());
                int flag = random.nextInt(2);

                if (flag==0 && neighbor.isLand()){
                    neighbor.setTileType(TileType.LAKE);
                    neighbor.addHumidity(mesh.getSoilType());
                }
            }
        }
        return mesh;
    }
}