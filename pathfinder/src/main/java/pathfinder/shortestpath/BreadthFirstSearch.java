package pathfinder.shortestpath;

import java.util.*;
import pathfinder.adt.Graph;
import pathfinder.adt.Node;

public class BreadthFirstSearch implements PathBetweenTwoNodes {
    @Override
    public List<String> calculatePath(Graph graph, String startNode, String endNode) {
        Map<String, Node> nodes = graph.getNodes();
        Map<String, String> parents = new HashMap<>();
        List<Node> temp = new ArrayList<>();
        temp.add(nodes.get(startNode));
        parents.put(startNode, null);

        while (temp.size() > 0) {
            Node currentNode = temp.get(0);
            for (Node neighbor : currentNode.getNeighbors()) {
                if (!parents.containsKey(neighbor.getName())) {
                    temp.add(neighbor);
                    parents.put(neighbor.getName(), currentNode.getName());
                    if (neighbor.getName().equals(endNode)) {
                        return getPath(parents, endNode);
                    }
                }
            }
            temp.remove(0);
        }
        return null;
    }

    public static List<String> getPath(Map<String, String> parents, String endNodeName) {
        List<String> path = new ArrayList<>();
        while (endNodeName != null) {
            path.add(0, endNodeName);
            endNodeName = parents.get(endNodeName);
        }
        return path;
    }
}
