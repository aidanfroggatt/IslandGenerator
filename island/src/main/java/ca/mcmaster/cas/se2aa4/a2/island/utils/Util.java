package ca.mcmaster.cas.se2aa4.a2.island.utils;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.BiomeType;
import ca.mcmaster.cas.se2aa4.a2.island.adt.CityType;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Polygon;

import java.util.List;

public class Util {
    public static double getCircleVal(double cx, double cy, Structs.Vertex vertex) {
        return Math.sqrt(Math.pow((cx - vertex.getX()), 2) + Math.pow((cy - vertex.getY()), 2));
    }

    public static double getDistanceBetweenTwoPoints(double ax, double ay, double bx, double by) {
        return Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
    }

    public static Structs.Property getProperty(String key, String value) {
        return Structs.Property.newBuilder().setKey(key)
                .setValue(String.valueOf(value))
                .build();
    }

    public static Structs.Property getProperty(String key, Integer value) {
        return Util.getProperty(key, String.valueOf(value));
    }

    public static String getBiomeColor(BiomeType biomeType) {
        if (biomeType == BiomeType.TROPICAL_RAIN_FOREST) {
            return "8,249,54";
        } else if (biomeType == BiomeType.TEMPERATE_RAIN_FOREST) {
            return "171,221,174";
        } else if (biomeType == BiomeType.SAVANNA) {
            return "155,224,35";
        } else if (biomeType == BiomeType.DESSERT) {
            return "249,148,24";
        } else if (biomeType == BiomeType.TEMPERATE_FOREST) {
            return "59,178,92";
        } else if (biomeType == BiomeType.TEMPERATE_DESERT) {
            return "250,219,7";
        } else if (biomeType == BiomeType.TAIGA) {
            return "5,102,33";
        } else {
            return "87,234,249";
        }
    }

    public static double[] getMinMaxHumidityAltitude(List<Polygon> polygons) {
        double minHumidity = Double.MAX_VALUE, maxHumidity = Double.MIN_VALUE, minAltitude = Double.MAX_VALUE, maxAltitude = Double.MIN_VALUE;
        double humidity, altitude;

        for (Polygon polygon : polygons) {
            humidity = polygon.getHumidity();
            altitude = polygon.getAltitude();

            if (humidity < minHumidity) {
                minHumidity = humidity;
            }
            if (humidity > maxHumidity) {
                maxHumidity = humidity;
            }

            if (altitude < minAltitude) {
                minAltitude = altitude;
            }
            if (altitude > maxAltitude) {
                maxAltitude = altitude;
            }
        }
        return new double[]{minHumidity, maxHumidity, minAltitude, maxAltitude};
    }

    public static int processResourcesProduction(BiomeType biomeType, double humidity, double altitude) {
        if (biomeType == BiomeType.TAIGA) {
            if (humidity > 0.5 && altitude > 0.5) {
                return 5;
            } else if (humidity > 0.5) {
                return 3;
            }
            return 1;
        } else if (biomeType == BiomeType.TEMPERATE_RAIN_FOREST) {
            if (humidity > 0.5 && altitude > 0.5) {
                return 2;
            } else if (humidity > 0.5) {
                return 5;
            }
            return 1;
        } else if (biomeType == BiomeType.TEMPERATE_FOREST) {
            if (humidity > 0.5 && altitude > 0.5) {
                return 2;
            }
            return 5;
        } else if (biomeType == BiomeType.TROPICAL_RAIN_FOREST) {
            if (humidity > 0.5 && altitude > 0.5) {
                return 1;
            } else if (humidity > 0.5) {
                return 5;
            }
            return 3;
        } else if (biomeType == BiomeType.SAVANNA) {
            if (humidity > 0.5 && altitude > 0.5) {
                return 2;
            } else if (humidity > 0.5) {
                return 3;
            }
            return 4;
        } else if (biomeType == BiomeType.TEMPERATE_DESERT) {
            return 1;
        }
        return 0;
    }

    public static float cityToSize(CityType c){
        if (c==null){
            return 0;
        }
        return switch (c) {
            case HAMLET -> 5.0f;
            case VILLAGE -> 10.0f;
            case TOWN -> 15.0f;
            case CITY -> 25.0f;
        };
    }
}
