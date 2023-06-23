package ca.mcmaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private final ArrayList<Segment> segments;
    private final List<Structs.Vertex> vertices;
    private List<Polygon> polygons;
    private double width, height;
    private SoilType soilType = null;

    public Mesh(List<Segment> segments, List<Structs.Vertex> vertices) {
        this.polygons = new ArrayList<>();
        this.segments = new ArrayList<>(segments);
        this.vertices = vertices;
        this.width = Double.MIN_VALUE;
        this.height = Double.MIN_VALUE;
        for (Structs.Vertex v : vertices) {
            this.width = (Double.compare(this.width, v.getX()) < 0 ? v.getX() : this.width);
            this.height = (Double.compare(this.height, v.getY()) < 0 ? v.getY() : this.height);
        }
    }

    public Mesh(List<Polygon> polygons, List<Segment> segments, List<Structs.Vertex> vertices) {
        this(segments, vertices);
        this.polygons.addAll(polygons);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Structs.Vertex> getVertices() {
        return vertices;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    public void removePolygon(Polygon polygon) {
        polygons.remove(polygon);
    }

    public void addPolygon(Polygon polygon) {
        polygons.add(polygon);
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }
}