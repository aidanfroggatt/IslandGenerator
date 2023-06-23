package ca.mcmaster.cas.se2aa4.a2.island;

import java.util.Random;

public class RandomWrapper {
    protected String seed;
    protected Random random = new Random();

    public void addSeed(String seed) {
        long seedL;
        try {
            seedL = Long.parseLong(seed);
        } catch (Exception e) {
            return;
        }
        this.seed = seed;
        random = new Random(seedL);
    }
}
