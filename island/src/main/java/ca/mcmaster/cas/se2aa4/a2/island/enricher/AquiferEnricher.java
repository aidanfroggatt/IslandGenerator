package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;

import java.util.List;

public class AquiferEnricher extends Enricher {

    int numOfAquifers;

    public AquiferEnricher(int numOfAquifers) {
        if (numOfAquifers < 0) {
            throw new UnsupportedOperationException("Unsupported number of aquifers " + numOfAquifers + ". Please make sure that the number of aquifers is greater than or equal to 0.");
        }
        this.numOfAquifers = numOfAquifers;
    }


    public AquiferEnricher(String numOfAquifers) {
        this(Integer.parseInt(numOfAquifers));
    }

    @Override
    public Mesh process(Mesh mesh) {
        List<Polygon> polygons = mesh.getPolygons();
        int numOfPolygons = polygons.size();
        Polygon currPolygon;
        int randPolygonIdx;
        for (int i = 0; i < numOfAquifers; i++) {
            randPolygonIdx = random.nextInt(numOfPolygons);
            currPolygon = polygons.get(randPolygonIdx);
            if (!currPolygon.isLand()) {
                i--;
                continue;
            }
            currPolygon.addHumidity(mesh.getSoilType());
            for (Polygon neighbor : currPolygon.getNeighbors()) {
                neighbor.addHumidity(mesh.getSoilType());
            }
        }
        return mesh;
    }
}
