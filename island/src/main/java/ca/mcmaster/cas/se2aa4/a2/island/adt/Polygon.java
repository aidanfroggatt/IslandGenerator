package ca.mcmaster.cas.se2aa4.a2.island.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.Colors;
import ca.mcmaster.cas.se2aa4.a2.island.tiles.TileType;
import ca.mcmaster.cas.se2aa4.a2.island.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Polygon {

    private int humidity = 0;
    private List<Polygon> neighbors;
    private Structs.Polygon polygon;
    private ArrayList<Structs.Property> properties;
    private TileType tileType;
    private Integer altitude = null;
    private String color = "255,255,255";
    private BiomeType biomeType;
    private int resourceProduction = 0;
    private CityType cityType;

    public BiomeType getBiomeType() {
        return biomeType;
    }

    public void setBiomeType(BiomeType biomeType) {
        this.biomeType = biomeType;
    }

    public Polygon(Structs.Polygon polygon, List<Structs.Property> propertiesList) {
        this.polygon = polygon;
        this.properties = new ArrayList<>(propertiesList);
        neighbors = new ArrayList<>();
    }

    public Structs.Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Structs.Polygon polygon) {
        this.polygon = polygon;
    }

    public void addNeighbor(Polygon polygon) {
        neighbors.add(polygon);
    }

    public List<Polygon> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Polygon> neighbors) {
        this.neighbors = neighbors;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
        this.color = Colors.getColor(tileType);
    }

    public void setBeachIfNearOcean() {
        // If Polygon Type is not land or null return
        if (this.getTileType() != TileType.LAND && this.getTileType() != null) {
            return;
        }

        if (shouldBeBeach()) {
            this.setTileType(TileType.BEACH);
        }
    }

    public boolean shouldBeBeach() {
        // If one of the neighbors is ocean it should be beach
        for (Polygon neighbor : neighbors) {
            if (neighbor.getTileType() == TileType.OCEAN) {
                return true;
            }
        }
        return false;
    }

    public boolean isOcean() {
        return this.getTileType() == TileType.OCEAN;
    }

    public List<Structs.Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Structs.Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Structs.Property property) {
        this.properties.add(property);
    }

    public Integer getAltitude() {
        return this.altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public void addHumidity(SoilType soilType) {
        if (soilType == null) {
            this.humidity++;
            return;
        }
        switch (soilType) {
            case DRY -> {
                this.humidity += 3;
            }
            case MODERATE -> {
                this.humidity += 2;
            }
            default -> this.humidity++;
        }
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isLand() {
        return this.tileType == null || this.tileType == TileType.LAND;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void addResourceProduction() {
        this.resourceProduction++;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(int resourceProduction) {
        this.resourceProduction = resourceProduction;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }
    public CityType getCityType() {
        return this.cityType;
    }

    public float getCitySize() {
        return Util.cityToSize(cityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        return "Polygon(" + this.polygon.getSegmentIdxsList() + ")";
    }
}