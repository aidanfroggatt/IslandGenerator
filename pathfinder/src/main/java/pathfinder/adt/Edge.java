package pathfinder.adt;

public class Edge {
    String name;
    Node node1;
    Node node2;
    public Edge(String name, Node node1, Node node2) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return this.name;
    }
}
