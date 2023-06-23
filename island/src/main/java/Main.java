import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.island.adt.io.Exporter;
import ca.mcmaster.cas.se2aa4.a2.island.adt.io.Importer;
import ca.mcmaster.cas.se2aa4.a2.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.Enricher;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.EnricherFactory;
import ca.mcmaster.cas.se2aa4.a2.island.enricher.biomes.Biomes;
import ca.mcmaster.cas.se2aa4.a2.island.heatmap.HeatmapFactory;
import ca.mcmaster.cas.se2aa4.a2.island.shapes.ShapeFactory;
import ca.mcmaster.cas.se2aa4.a2.island.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.island.specification.SpecificationFactory;


public class Main {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration(args);
        Structs.Mesh inputMesh = new MeshFactory().read(config.input());
        Buildable specification;

        if (config.export().containsKey(Configuration.MODE_SHORT)) {
            specification = SpecificationFactory.create(config);
        } else {
            specification = ShapeFactory.create(config);
        }


        Mesh processedMesh = specification.build(new Importer().importMesh(inputMesh));
        EnricherFactory.enrich(config, processedMesh);
        if (config.export().containsKey(Configuration.BIOMES_SHORT)) {
            Enricher enricher = new Biomes(config.export(Configuration.BIOMES_SHORT));
            enricher.process(processedMesh);
        }
        new MeshFactory().write(new Exporter().exportMesh(processedMesh), config.export(Configuration.OUTPUT));

        if (config.export().containsKey(Configuration.HEATMAP_SHORT)) {
            Buildable heatmapBuildable = HeatmapFactory.create(config);
            heatmapBuildable.build(processedMesh);
            new MeshFactory().write(new Exporter().exportMesh(processedMesh), config.export(Configuration.HEATMAP_OUTPUT_SHORT));
        }
    }

}
