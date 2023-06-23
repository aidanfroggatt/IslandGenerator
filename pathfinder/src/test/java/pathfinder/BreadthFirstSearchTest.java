package pathfinder;

import org.junit.jupiter.api.Test;
import pathfinder.adt.Graph;
import pathfinder.shortestpath.BreadthFirstSearch;
import pathfinder.shortestpath.PathBetweenTwoNodes;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class BreadthFirstSearchTest {
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

    //note that the path length is the number of nodes along the path, not the number of edges
    //ie. the path length of A->B->C is 3, not 2
    @Test
    void testBFSShortestPath1() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "A", "D");
        assertEquals(3,path.size());
    }
    @Test
    void testBFSShortestPath2() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "A", "B");
        assertEquals(2, path.size());
    }
    @Test
    void testBFSShortestPath3() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "A", "E");
        assertEquals(3, path.size());
    }
    @Test
    void testBFSShortestPath4() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "E", "A");
        assertEquals(3, path.size());
    }
    //no path can exist to a node that is not connected to any other node
    @Test
    void testBFSShortestPathToDisconnectedNode() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "A", "F");
        assertNull(path);
    }
    @Test
    void testBFSShortestPathToNonexistentNode() {
        Graph graph = this.testGraph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        List<String> path = BFS.calculatePath(graph, "A", "G");
        assertNull(path);
    }
    @Test
    void testBFSShortestPathEmptyGraph() {
        Graph graph = new Graph();
        PathBetweenTwoNodes BFS = new BreadthFirstSearch();
        assertThrows
                (NullPointerException.class, () -> BFS.calculatePath(graph, "A", "E"));
    }
}
