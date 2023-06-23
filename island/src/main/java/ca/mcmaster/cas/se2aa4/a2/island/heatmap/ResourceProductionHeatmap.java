package ca.mcmaster.cas.se2aa4.a2.island.heatmap;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;

public class ResourceProductionHeatmap implements Buildable {
    @Override
    public Mesh build(Mesh aMesh) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Polygon polygon : aMesh.getPolygons()) {
            if (polygon.getResourceProduction() < min) {
                min = polygon.getResourceProduction();
            }
            if (polygon.getResourceProduction() > max) {
                max = polygon.getResourceProduction();
            }
        }
        double divisions = (double) 255 / (double) max;
        int color;
        for (Polygon polygon : aMesh.getPolygons()) {
            if (polygon.isOcean()) {
                polygon.setColor("0,0,0");
                continue;
            }
            color = (int) Math.ceil(divisions * polygon.getResourceProduction());
            color = color < 0 ? 0 : color;
            color = 255 - color;
            polygon.setColor(color + "," + color + ",255");
        }
        return aMesh;
    }
}
