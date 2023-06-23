package pathfinder.adt;

import java.util.*;

public class Graph {
    private final Map<String, Node> NODES = new HashMap<>();
    private final Map<String, Edge> EDGES = new HashMap<>();
    public Graph() {}

    public void addEdge(String nodeName1, String nodeName2) {
        Node node1 = isNodeNull(nodeName1);
        Node node2 = isNodeNull(nodeName2);

        node1.addNeighbor(node2);
        node2.addNeighbor(node1);

        NODES.put(nodeName1, node1);
        NODES.put(nodeName2, node2);

        //I'm assuming that all edges are bidirectional
        EDGES.put(nodeName1 + nodeName2, new Edge(nodeName1 + nodeName2, node1, node2));
        EDGES.put(nodeName2 + nodeName1, new Edge(nodeName2 + nodeName1, node2, node1));
    }

    private Node isNodeNull(String nodeName2) {
        Node node2 = NODES.get(nodeName2);
        node2 = isNodeNullHelper(nodeName2, node2);
        return node2;
    }

    private static Node isNodeNullHelper(String nodeName2, Node node2) {
        if (node2 == null) {
            node2 = new Node(nodeName2);
        }
        return node2;
    }

    public void addNode(String nodeName) {
        Node node = isNodeNull(nodeName);
        NODES.put(nodeName, node);
    }
    public Map<String, Node> getNodes() {
        return NODES;
    }
    public Map<String, Edge> getEdges() { return EDGES; }
    public int getNumberOfNodes() {
        return NODES.size();
    }
    //assuming edges are bidirectional
    public int getNumberOfEdges() {
        return EDGES.size()/2;
    }
}