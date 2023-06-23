# Assignment A4: Urbanism

  - Aidan Froggatt [froggata@mcmaster.ca]
  - A Readme.md file documenting your project (author, rationale, and explanations for
    extending the library by implementing a new algorithm)
  - Rationale: This is a graph ADT and pathfinding library that can be extended with new algorithms to find the shortest path between two nodes.
  - explanations for
    extending the library by implementing a new algorithm:
    - One could take user input with CLI to choose which algorithm they wish to use to find a path between two nodes
    - A new implementation of pathBetweenTwoNodes can be created overriding the calculatePath method to use a different algorithm

# Cities
    
    - Note that cities are represented by black marks at the center points of the tiles
    - There will be one square city which represents the hub/ center of the star network


## How to generate cities
    java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape irregular --cities 3
add the --cities flag to specify the number of cities to generate
## How to use
    
      - To use this library, you must first create a graph object. This can be done by creating a new Graph object and adding nodes and edges to it. 
     - Nodes can be added by calling the addNode method on the graph object. This method takes a string as a parameter and returns a Node object. 
     - Edges can be added by calling the addEdge method on the graph object. This method takes two Node objects as parameters and returns an Edge object. 
      - Once the graph is created, you can find the shortest path between two nodes by calling the pathBetweenTwoNodes method on the graph object. This method takes two Node objects as parameters and returns a list of Node objects. 
      - The graph object can be printed by calling the printGraph method on the graph object. This method takes no parameters and returns nothing. 
      - The graph object can be saved to a file by calling the saveGraph method on the graph object. This method takes a string as a parameter and returns nothing. 
      - The graph object can be loaded from a file by calling the loadGraph method on the graph object. This method takes a string as a parameter and returns nothing. 
      - The graph object can be cleared by calling the clearGraph method on the graph object. This method takes no parameters and returns nothing. 
      - The graph object can be checked to see if it is empty by calling the isEmpty method on the graph object. This method takes no parameters and returns a boolean. 
      - The graph object can be checked to see if it contains a node by calling the containsNode method on the graph object. This method takes a string as a parameter and returns a boolean. 
      - The graph object can be checked to see if it contains an edge by calling the containsEdge method on the graph object. This method takes two Node objects as parameters and returns a boolean. 
      - The graph object can be checked to see if it contains a path by calling the containsPath method on the graph object. This method takes a list of Node objects as parameters and returns a boolean. 
      - The graph object can be checked to see if it contains a cycle by calling the containsCycle method on the graph object. This method takes no parameters and returns a boolean. 
      - The graph object can be checked to see if it contains a cycle by calling the containsCycle method on the graph object. This method takes no parameters

