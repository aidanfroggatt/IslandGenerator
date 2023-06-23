package ca.mcmaster.cas.se2aa4.a2.island.configuration;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    public static final String INPUT = "i";
    public static final String OUTPUT = "o";
    public static final String MODE_SHORT = "m";
    public static final String MODE_LONG = "mode";
    public static final String ALTITUDE_SHORT = "a";
    public static final String ALTITUDE_LONG = "altitude";
    public static final String AQUIFER_SHORT = "aq";
    public static final String AQUIFERS_LONG = "aquifers";
    public static final String BIOMES_SHORT = "b";
    public static final String BIOMES_LONG = "biomes";
    public static final String HEATMAP_LONG = "heatmap";
    public static final String HEATMAP_SHORT = "hm";
    public static final String HEATMAP_OUTPUT_LONG = "heatmapOut";
    public static final String HEATMAP_OUTPUT_SHORT = "ho";
    public static final String LAKES_LONG = "lakes";
    public static final String LAKES_SHORT = "la";
    public static final String RIVER_LONG = "rivers";
    public static final String RIVER_SHORT = "r";
    public static final String SEED_LONG = "seed";
    public static final String SEED_SHORT = "se";
    public static final String SHAPE_LONG = "shape";
    public static final String SHAPE_SHORT = "sp";
    public static final String SOIL_LONG = "soil";
    public static final String SOIL_SHORT = "so";
    public static final String CITIES_LONG = "cities";
    public static final String CITIES_SHORT = "ci";
    public static final String HELP = "help";

    private final CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
            if (cli.hasOption(HELP)) {
                help();
            }
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for (Option o : cli.getOptions()) {
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    public String export(String key) {
        return cli.getOptionValue(key);
    }

    public String input() {
        return this.cli.getOptionValue(INPUT);
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file name"));
        options.addOption(new Option(OUTPUT, true, "Output file name"));
        options.addOption(new Option(MODE_SHORT, MODE_LONG, true, "Mode of Island"));
        options.addOption(new Option(SHAPE_SHORT, SHAPE_LONG, true, "Choose island shape: circle, square or diamond"));
        options.addOption(new Option(ALTITUDE_SHORT, ALTITUDE_LONG, true, "Altitude Type, possible values volcano, arctic, hill and plateau"));
        options.addOption(new Option(AQUIFER_SHORT, AQUIFERS_LONG, true, "To add aquifers in the mesh. This option takes number of aquifers as argument"));
        options.addOption(new Option(RIVER_SHORT, RIVER_LONG, true, "To add rivers in the mesh. This option takes number of rivers as argument"));
        options.addOption(new Option(SOIL_SHORT, SOIL_LONG, true, "To add a soil profile to the mesh. This option takes in a profile name"));
        options.addOption(new Option(SEED_SHORT, SEED_LONG, true, "Use a specific seed"));
        options.addOption(new Option(LAKES_SHORT, LAKES_LONG, true, "Pick a number of lakes greater than 0"));
        options.addOption(new Option(HEATMAP_SHORT, HEATMAP_LONG, true, "Heatmap selector the possible values are elevation, humidity or resource"));
        options.addOption(new Option(HEATMAP_OUTPUT_SHORT, HEATMAP_OUTPUT_LONG, true, "Heatmap output file"));
        options.addOption(new Option(BIOMES_SHORT, BIOMES_LONG, true, "Biome selector the possible values are brazil, canada and egypt."));
        options.addOption(new Option(CITIES_SHORT, CITIES_LONG, true, "Number of cities to generate"));
        options.addOption(new Option(HELP, false, "print help message"));
        return options;
    }

}
