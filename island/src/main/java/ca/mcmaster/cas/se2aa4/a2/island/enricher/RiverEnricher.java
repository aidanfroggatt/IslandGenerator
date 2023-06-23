package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Segment;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.Colors;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RiverEnricher extends Enricher {

    int numOfRivers;
    List<Polygon> polygons;
    List<Segment> segments;
    List<Integer> riverSegmentIdxs;
    HashSet<Polygon> visitedPolygons;

    public RiverEnricher(int numOfRivers) {
        if (numOfRivers < 0) {
            throw new UnsupportedOperationException("Unsupported number of rivers " + numOfRivers + ". Please make sure that the number of rivers is greater than or equal to 0.");
        }
        this.numOfRivers = numOfRivers;
        this.riverSegmentIdxs = new ArrayList<>();
        this.visitedPolygons = new HashSet<>();
    }

    public RiverEnricher(String numOfRivers) {
        this(Integer.parseInt(numOfRivers));
    }

    @Override
    public Mesh process(Mesh mesh) {
        int randVertexIdx;
        polygons = mesh.getPolygons();
        segments = mesh.getSegments();
        Segment currSegment;
        Polygon currPolygon;
        int riverFlow;
        int currSegmentIdx = -1;

        // Iterating for number of rivers
        for (int i = 0; i < numOfRivers; i++) {
            visitedPolygons.clear();
            currSegment = null;
            currPolygon = null;
            riverFlow = random.nextInt(3)+1;
            // Getting random vertex as a start of river
            randVertexIdx = random.nextInt(mesh.getVertices().size());

            // Iterating over each polygon
            for (Polygon polygon : polygons) {

                // Iterating over each segment of this polygon
                for (Integer segmentIdx : polygon.getPolygon().getSegmentIdxsList()) {

                    currSegmentIdx = segmentIdx;
                    // if segment has the river starting vertex, setting this segment and polygon as starting of this river
                    currSegment = segments.get(segmentIdx);
                    if (currSegment.getBaseSegment().getV2Idx() == randVertexIdx || currSegment.getBaseSegment().getV1Idx() == randVertexIdx) {
                        currPolygon = polygon;
                        break;
                    }
                }

                // If the river starting polygon is found break the polygon loop
                if (currPolygon != null) {
                    break;
                }
            }

            // If river starting polygon is not found or polygon is not land
            // restart current iteration of river generation
            if (currPolygon == null || !currPolygon.isLand()) {
                i--;
                continue;
            }

            // Setting the starting segment as river
            currSegment.setColor(Colors.OCEAN_COLOR);
            currSegment.increaseThickness(riverFlow);
            riverSegmentIdxs.add(currSegmentIdx);

            // Function call to process river going downstream
            processRiverDownStream(currPolygon, randVertexIdx, riverFlow);
        }

        // Iterating over river segments
        for(int idx: riverSegmentIdxs) {

            // Iterating over polygons to check if river is in polygon then adding humidity
            for(Polygon polygon: polygons) {
                if(polygon.getPolygon().getSegmentIdxsList().contains(idx)) {
                    polygon.addHumidity(mesh.getSoilType());
                }
            }
        }
        return mesh;
    }

    private boolean processRiverDownStream(Polygon polygon, Integer vertexIdx, int riverFlow) {

        // If polygon is ocean or lagoon return that the end of river is found
        if (polygon.isOcean() || polygon.getTileType() == TileType.LAGOON) {
            return true;
        }

        // If the current polygon is already visited return as river end not found
        if(visitedPolygons.contains(polygon) ) {
            return false;
        }
        visitedPolygons.add(polygon);
        Segment currSegment;
        boolean hasFoundDownstream = false;

        // Iterating over each neighbor
        for (Polygon neighbor : polygon.getNeighbors()) {

            // If neighbor has higher altitude than current, then continue to over more neighbors
            if (neighbor.getAltitude() > polygon.getAltitude()) {
                continue;
            }

            // Iterating over segments of neighbor
            for (Integer segmentIdx : neighbor.getPolygon().getSegmentIdxsList()) {

                // If current segment shares last river vertex
                currSegment = segments.get(segmentIdx);
                if (currSegment.getBaseSegment().getV1Idx() == vertexIdx || currSegment.getBaseSegment().getV2Idx() == vertexIdx) {
                    int nextVertexIdx = currSegment.getBaseSegment().getV1Idx() == vertexIdx ? currSegment.getBaseSegment().getV2Idx() : currSegment.getBaseSegment().getV1Idx();

                    // Function call to set current neighbor river polygon and next vertex for river
                    // If River down stream is found
                    // Setting the segment as river
                    // break iterating over segments and neighbors
                    hasFoundDownstream = processRiverDownStream(neighbor, nextVertexIdx, riverFlow);
                    if (hasFoundDownstream) {
                        riverSegmentIdxs.add(segmentIdx);
                        currSegment.setColor(Colors.OCEAN_COLOR);
                        currSegment.increaseThickness(riverFlow);
                        break;
                    }
                }
            }
            if(hasFoundDownstream) {
                break;
            }
        }
        return true;
    }
}