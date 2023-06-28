# Assignment A2: Mesh Generator

  - [Aidan Froggatt](https://github.com/aidanfroggatt) [froggata@mcmaster.ca]
  - Ashwin Unnithan [unnithaa@mcmaster.ca]
  - Krish Dogra [dograk1@mcmaster.ca]

## A4 Extension: Urbanism
  - Aidan Froggatt [froggata@mcmaster.ca]
  - Full Readme available in the **pathfinder** folder


## How to run the product

_This section needs to be edited to reflect how the user can interact with thefeature released in your project_

## Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

## Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator 
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

To Pass Options

```java -jar generator.jar -out sample.mesh -mesh irregular -numPoly 100 -relaxationLevel 10 -wavefront sample.obj```

## Island

To convert an existing mesh to island, go the the `island` directory, and use `java -jar` to run the product. The product take three arguments (so far): the file containing the mesh, the name of the file to store the island mesh (as a mesh file), and mode of the island.
### To Generate a sandbox
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --mode lagoon
```

### To Generate Cities/Roads
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --cities 3
```
The number of cities can be changed.\
There is always one capital city represented as a black square.\
All other cities are dark gray smaller circles

### To Generate a realistic island the following options can be used:
#### Shapes

To pass island shape specifications, use the command format below

```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle
```

Possible values for shapes are:
- circle
- oval
- irregular

#### Altitude 
Altitude profile can be passed by
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --altitude volcano
```
or 
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -a volcano
```
Possible values for altitude are: 
- artic
- hills
- plateau
- valcano

#### Aquifers
Number of Aquifers can be passed by
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --aquifers (Number of aquifers)
```
or
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -aq (Number of aquifers)
```

#### Biomes
This is to pass the biome for the island
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --biome type_of_biome
```
or
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -b type_of_biome
```
Possible type of biomes are:
- brazil
- canada
- egypt

#### Lakes
Number of Lakes can be passed by
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --lakes (Number of lakes)
```
or
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -la (Number of lakes)
```

#### Rivers
Number of Rivers can be passed by
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --rivers (Number of rivers)
```
or
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -r (Number of rivers)
```

#### Soils
This is to pass the soil composition for the island
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --soil type_of_soil
```
or
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle -so type_of_soil
```
Possible type of soil are:
- dry
- moderate
- wet

### To Generate a heatmap
```
mosser@azrael A2 % java -jar island/island.jar -i img/irregular.mesh -o img/island.mesh --shape circle --biome canada --heatmap type_of_heatmap --heatmapOut heatmap_output_file
```
Possible values for type of heatmap:
- altitude
- humidity
- resource

## Visualizer

To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
mosser@azrael A2 % cd visualizer 
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/island.mesh -o img/island.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/island.mesh -o img/island_debug.svg -x

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```

### Running Visualizer Debug Mode using CLI commands
- Activate Debug Mode using "-X" flag:
  - `java -jar visualizer.jar ../generator/sample.mesh sample.svg -X` (default RGB colouring is taken otherwise)


To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.



## Backlog

### Definition of Done

* Full and thorough completion of features in steps 1, 2, and 3 including properties:
   * Accepts the correct required inputs and parameters
   * Correctly processes and returns the associated output
   * Clear of errors and exceptions interfering with mesh generation/visualization/processing


### A2 Product Backlog
| Id | Feature title | Who? | Start | End | Status |
| :-: |:-:  |---       | :-:     | :-:       | :-:       |
| F01 | Draw Segments between Vertices to visualize the Squares | Ashwin Unnithan, Krish Dogra, Aidan Froggatt  | 2023/02/09 | 2023/02/18 | D |
| F02 | Playing with rendering | Aidan Froggatt | 2023/02/17 | 2023/02/17 | D |
| F03 | Creating a mesh ADT | Krish Dogra | 2023/02/17 | 2023/02/20 | D |
| F04 | Producing full meshes | Krish Dogra | 2023/02/17 | 2023/02/20 | D |
| F05 | Visualization: Debug Mode | Ashwin Unnithan | 2023/02/19 | 2023/02/24  | D |
| F06 | Generating Voronoi Diagram | Krish Dogra | 2023/02/22 | 2023/02/24  | D |
| F07 | Apply Lloyd relaxation | Krish Dogra | 2023/02/25 | 2023/02/25 | D |
| F08 | Crop Irregular Mesh | Ashwin Unnithan | 2023/02/25 | 2023/02/25  | D |
| F09 | Compute neighbourhood relationships using Delaunay's Triangulation | Aidan Froggatt | 2023/02/22 | 2023/02/26 | D |
| F10 | Use Convex hull | Ashwin Unnithan | 2023/02/26 | 2023/02/27 | D |
| F11 | Control Mesh generation via CLI | Krish Dogra | 2023/02/26 | 2023/02/27 | D |
| F12 | Create Wavefront OBJ File | Aidan Froggatt | 2023/02/27 | 2023/02/27 | D |
| ... | ... | ... |

### A3 Product Backlog
| Id | Feature title | Who? | Start | End | Status |
| :-: |:-:  |---       | :-:     | :-:       | :-:       |
| F13 | Sandbox | Krish Dogra | 2023/03/09 | 2023/03/14 | D |
| F14 | Elevation - Altitude (Volcano, Artic, Hills, Plateau) | Krish Dogra | 2023/03/17 | 2023/03/21 | D |
| F15 | Aquifers | Krish Dogra | 2023/03/21 | 2023/03/21 | D |
| F16 | Rivers / River Flow | Krish Dogra | 2023/03/21 | 2023/03/22 | D |
| F17 | Shapes - Circular | Ashwin Unnithan | 2023/03/17 | 2023/03/24 | D |
| F18 | Shapes - Oval | Ashwin Unnithan | 2023/03/17 | 2023/03/25 | D |
| F19 | Shapes - Irregular | Ashwin Unnithan | 2023/03/25 |  2023/03/25 | D |
| F20 | Lakes | Aidan Froggatt | 2023-03-22 | 2023-03-22 | D |
| F21 | Soil Absorption - Dry Soil | Aidan Froggatt | 2023/03/22 | 2023/03/25 | D |
| F22 | Soil Absorption - Wet Soil | Aidan Froggatt | 2023/03/22 | 2023/03/25 | D |
| F23 | Soil Absorption - Moderate Soil | Aidan Froggatt | 2023/03/22 | 2023/03/25 | D |
| F24 | Whitaker Diagrams - Canada | Ashwin Unnithan | 2023/03/26 | 2023/03/26 | D |
| F25 | Whitaker Diagrams - Egypt | Ashwin Unnithan | 2023/03/26 | 2023/03/26 | D |
| F26 | Whitaker Diagrams - Brazil | Ashwin Unnithan | 2023/03/26 | 2023/03/26 | D |
| F27 | Biomes | Ashwin Unnithan | 2023/03/26 | 2023/03/26 | D |
| F28 | Reproducibility | Aidan Froggatt | 2023/03/22 | 2023/03/26 | D |
| Bonus: F29 | Resource Production | Krish Dogra | 2023/03/26 | 2023/03/26 | D |
| Bonus: F30 | Heatmaps | Krish Dogra | 2023/03/26 | 2023/03/26 | D |












