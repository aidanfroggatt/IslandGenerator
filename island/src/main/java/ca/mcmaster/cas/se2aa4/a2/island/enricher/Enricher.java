package ca.mcmaster.cas.se2aa4.a2.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.island.RandomWrapper;
import ca.mcmaster.cas.se2aa4.a2.island.adt.Mesh;

import java.util.Random;

public abstract class Enricher extends RandomWrapper {

    public abstract Mesh process(Mesh mesh);
}
