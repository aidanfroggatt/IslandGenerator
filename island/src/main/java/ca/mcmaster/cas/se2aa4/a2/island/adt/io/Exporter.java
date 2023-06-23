package ca.mcmaster.cas.se2aa4.a2.island.adt.io;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Segment;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class Exporter {
    public Structs.Mesh exportMesh(Mesh mesh) {
        List<Structs.Polygon> structPolygons = new ArrayList<>();
        for (Polygon polygon : mesh.getPolygons()) {
            Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(polygon.getPolygon())
                    .addAllProperties(polygon.getProperties())
                    .addProperties(Util.getProperty("rgb_color", polygon.getColor()));

            Integer humidity = polygon.getHumidity();
            builder.addProperties(Util.getProperty("humidity", humidity));

            // Adding altitude information
            Integer altitude = polygon.getAltitude() != null ? polygon.getAltitude() : 0;
            builder.addProperties(Util.getProperty("altitude", altitude));
            structPolygons.add(builder.build());


            Float citySize = polygon.getCitySize();
            builder.addProperties(Util.getProperty("city_size", String.valueOf(citySize)));
            structPolygons.add(builder.build());
        }


        List<Structs.Segment> structSegments = new ArrayList<>();
        for (Segment segment : mesh.getSegments()) {
            Structs.Segment.Builder builder = Structs.Segment.newBuilder(segment.getBaseSegment());

            if(segment.getColor() != null) {
                builder.addProperties(Util.getProperty("rgb_color", segment.getColor()));
            }

            if(segment.getThickness() != null) {
                builder.addProperties(Util.getProperty("thickness", segment.getThickness()));
            }
            structSegments.add(builder.build());
        }
        return Structs.Mesh.newBuilder().addAllVertices(mesh.getVertices()).addAllSegments(structSegments).addAllPolygons(structPolygons).build();
    }
}
