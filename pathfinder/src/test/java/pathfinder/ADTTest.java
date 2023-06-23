package pathfinder;

import org.junit.jupiter.api.Test;
import pathfinder.adt.Graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ADTTest {
    private Graph testGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");
        graph.addNode("F");
        return graph;
    }
    @Test
    void addExistingNode() {
        //graph should not change if we try to add an existing node
        Graph graph = this.testGraph();
        graph.addNode("A");
        //number of nodes should not change
        assertEquals(6, graph.getNodes().size());
        //number of edges should not changes
        assertEquals(7, graph.getNumberOfEdges());
    }
    @Test
    void addExistingEdge() {
        //graph should not change if we try to add an existing edge
        Graph graph = this.testGraph();
        graph.addEdge("A", "B");
        //number of nodes should not change
        assertEquals(6, graph.getNodes().size());
        //number of edges should not changes
        assertEquals(7, graph.getNumberOfEdges());
    }
    @Test
    void testGetNumberOfEdges() {
        Graph graph = this.testGraph();
        assertEquals(7, graph.getNumberOfEdges());
    }
    @Test
    void testGetNumberOfNodes() {
        Graph graph = this.testGraph();
        assertEquals(6, graph.getNumberOfNodes());
    }
}
