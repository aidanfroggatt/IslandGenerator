package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.CityProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ThicknessProperty;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class GraphicRenderer implements Renderer {
    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawSpecialSegments(aMesh, canvas);
        drawCities(aMesh, canvas);
//        drawRoads1(aMesh, canvas);
        shortestDirect(aMesh, canvas);
    }

    private void drawSpecialSegments(Mesh aMesh, Graphics2D canvas) {
        List<Vertex> vertices = aMesh.getVerticesList();
        Vertex v1, v2;
        Line2D.Double L2D;
        for(Structs.Segment segment: aMesh.getSegmentsList()){
            v1 = vertices.get(segment.getV1Idx());
            v2 = vertices.get(segment.getV2Idx());
            Optional<Color> fill = new ColorProperty().extract(segment.getPropertiesList());
            Optional<Integer> thickness = new ThicknessProperty().extract(segment.getPropertiesList());
            if(fill.isPresent() && thickness.isPresent()) {
                Color old = canvas.getColor();
                canvas.setColor(fill.get());
                canvas.setStroke(new BasicStroke(thickness.get()));
                L2D = new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY());
                canvas.draw(L2D);
                canvas.setColor(old);
            }
        }
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());
        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

    public void drawCities(Structs.Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()) {
            Optional<Float> city_size = new CityProperty().extract(p.getPropertiesList());
            Float size = city_size.orElse(0.0f);
            if (size>15.0f){
                canvas.setColor(Color.BLACK);
                Structs.Vertex centroid = aMesh.getVertices(p.getCentroidIdx());
                Rectangle2D square = new Rectangle2D.Float(
                        (float) centroid.getX() - size/2,
                        (float) centroid.getY() - size/2,
                        size,
                        size);
                canvas.fill(square);
            }
            else if (size>0.0f){
                canvas.setColor(Color.darkGray);
                Structs.Vertex centroid = aMesh.getVertices(p.getCentroidIdx());
                Ellipse2D circle = new Ellipse2D.Float(
                        (float) centroid.getX() - size/2,
                        (float) centroid.getY() - size/2,
                        size,
                        size);
                canvas.fill(circle);
            }
        }
    }

    public static void shortestDirect(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setStroke(new BasicStroke(THICKNESS));
        for (Structs.Polygon current : aMesh.getPolygonsList()) {
            for (Structs.Polygon check : aMesh.getPolygonsList()) {
                Optional<Float> current_size = new CityProperty().extract(current.getPropertiesList());
                Float current_sizer = current_size.orElse(0.0f);
                Optional<Float> check_size = new CityProperty().extract(check.getPropertiesList());
                Float check_sizer = check_size.orElse(0.0f);

                if (current_sizer > 20.0f && check_sizer > 0.0f) {
                    Structs.Vertex currentCentroid = aMesh.getVertices(current.getCentroidIdx());
                    Structs.Vertex checkCentroid = aMesh.getVertices(check.getCentroidIdx());
                    Line2D road = new Line2D.Double(
                            currentCentroid.getX(),
                            currentCentroid.getY(),
                            checkCentroid.getX(),
                            checkCentroid.getY());
                    canvas.draw(road);
                }
            }
        }
    }
}
