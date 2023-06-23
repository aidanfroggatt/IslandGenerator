package ca.mcmaster.cas.se2aa4.a2.island;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomWrapperTest {

    @Test
    void randomTest() {
        RandomWrapper randomWrapper1 = new RandomWrapper();
        RandomWrapper randomWrapper2 = new RandomWrapper();
        assert randomWrapper1.random.nextInt() != randomWrapper2.random.nextInt();
    }

    @Test
    void randomTestWithIncorrectSeed() {
        RandomWrapper randomWrapper1 = new RandomWrapper();
        randomWrapper1.addSeed("");
        RandomWrapper randomWrapper2 = new RandomWrapper();
        randomWrapper2.addSeed("");
        assert randomWrapper1.random.nextInt() != randomWrapper2.random.nextInt();
    }

    @Test
    void addSeed() {
        RandomWrapper randomWrapper1 = new RandomWrapper();
        randomWrapper1.addSeed("1");
        RandomWrapper randomWrapper2 = new RandomWrapper();
        randomWrapper2.addSeed("1");
        assert randomWrapper1.random.nextInt() == randomWrapper2.random.nextInt();
    }
}