package pathfinder.adt;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    List<pathfinder.adt.Node> neighbors = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void addNeighbor(pathfinder.adt.Node neighbor) {
        neighbors.add(neighbor);
    }
    public List<pathfinder.adt.Node> getNeighbors() {
        return neighbors;
    }
    public String toString() {
        return this.name;
    }
}
