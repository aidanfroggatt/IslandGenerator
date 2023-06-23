package pathfinder.shortestpath;

import pathfinder.adt.Graph;
import java.util.*;

public interface PathBetweenTwoNodes {
    List<String> calculatePath(Graph graph, String startNode, String endNode);
}
