package ca.mcmaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Segment {
    private Structs.Segment baseSegment;
    private String color = null;
    private Integer thickness = null;

    public Segment(Structs.Segment baseSegment) {
        this.baseSegment = baseSegment;
    }

    public Structs.Segment getBaseSegment() {
        return baseSegment;
    }

    public void setBaseSegment(Structs.Segment baseSegment) {
        this.baseSegment = baseSegment;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public void increaseThickness(int riverFlow) {
        if (thickness == null) {
            thickness = 0;
        }
        thickness+=riverFlow;
    }
}
