classDiagram
direction BT
class AddAntButton {
  + AddAntButton(boolean) 
  - AntPopulation antPopulation
   AntPopulation antPopulation
}
class Ant {
  + Ant(String, int, int, String, Anthill) 
  # int health
  # String color
  # int collected_larvae
  - int x
  # Vertex currentVertex
  # int strength
  # Stack~Vertex~ path
  - int y
  # Anthill anthill
  # boolean alive
  + die() void
  + dropLarvae(int) void
  + receiveDamage(int) void
  + randomMove() void
  + returnToAnthill() void
  + move(Vertex) void
  + storeLarvaeAsFood() void
   Vertex currentVertex
   int collected_larvae
   int strength
   String color
   String _Name
   int y
   int x
   int health
   Anthill anthill
   boolean alive
   Stack~Vertex~ path
}
class AntFactory {
  - AntFactory(ArrayList~String~, Anthill, Anthill) 
  + getInstance(ArrayList~String~, Anthill, Anthill) AntFactory
  - chooseRandomStats() void
  + initRedAnt() RedAnt
  + initBlueAnt() BlueAnt
}
class AntGraph {
  + AntGraph() 
  - AntPopulation antPopulation
  - World world
  + paintComponent(Graphics) void
  + updateGraph(Graphics) void
   World world
   AntPopulation antPopulation
}
class AntPopulation {
  - AntPopulation(int, int) 
  - int red_size
  - ArrayList~String~ names
  - ArrayList~BlueAnt~ blue_ants
  - AntFactory antFactory
  - World world
  - ArrayList~Ant~ ants
  - int blue_size
  - ArrayList~RedAnt~ red_ants
  - Semaphore ant_semaphore
  + AddBlueAnt() void
  + removeAnt(Ant) void
  + access() AntPopulation
  + start() void
  + getInstance(int, int) AntPopulation
  + AddRedAnt() void
   World world
   int red_size
   ArrayList~Ant~ ants
   ArrayList~String~ names
   Semaphore ant_semaphore
   int blue_size
   AntFactory antFactory
   ArrayList~BlueAnt~ blue_ants
   int size
   ArrayList~RedAnt~ red_ants
}
class Anthill {
  + Anthill(String, double, double) 
  - int amount_of_food
  + addFood(int) void
  + removeFood(int) void
   int amount_of_food
}
class BlueAnt {
  + BlueAnt(String, int, int, Anthill) 
  + storeLarvaeAsFood() void
  + die() void
  + move(Vertex) void
}
class BluePoints {
  + BluePoints(Anthill) 
  - Anthill blue
   Anthill blue
}
class Blunderer {
  + Blunderer(String, int, int, Anthill) 
  - double dropChance
  + returnToAnthill() void
   double dropChance
}
class Collecting {
<<Interface>>
  + collectLarvae() void
}
class Collector {
  + Collector(String, int, int, Anthill) 
  + run() void
  + collectLarvae() void
}
class DeadRemoval {
  + DeadRemoval(AntPopulation) 
  - AntPopulation antPopulation
  + run() void
   AntPopulation antPopulation
}
class Drone {
  + Drone(String, int, int, Anthill) 
  + run() void
}
class Dying {
<<Interface>>
  + receiveDamage(int) void
  + die() void
}
class Fighting {
<<Interface>>
  + attack() void
}
class GraphUpdater {
  + GraphUpdater(AntGraph) 
  # doInBackground() Void
}
class InfoPanel {
  + InfoPanel() 
  + updateInfo() void
}
class InfoUpdater {
  + InfoUpdater(InfoPanel) 
  # doInBackground() Void
}
class Leaf {
  + Leaf(String, int, double, double) 
  + lookForRedEnemy() RedAnt
  + lookForBlueEnemy() BlueAnt
}
class Main {
  + Main() 
  + main(String[]) void
}
class MainFrame {
  + MainFrame() 
  + run() void
}
class Moving {
<<Interface>>
  + randomMove() void
  + move(Vertex) void
  + returnToAnthill() void
}
class Pair {
  + Pair(int, int) 
  - int x
  - int y
   int y
   int x
}
class Points {
  + Points() 
  # World world
  # updateScore(int) void
   World world
}
class RedAnt {
  + RedAnt(String, int, int, Anthill) 
  + die() void
  + move(Vertex) void
  + storeLarvaeAsFood() void
}
class RedPoints {
  + RedPoints(Anthill) 
  - Anthill red
   Anthill red
}
class Returning {
<<Interface>>
  + returnToAnthill() void
}
class ScorePanel {
  + ScorePanel(RedPoints, BluePoints) 
  + updateScore() void
}
class ScoreUpdater {
  + ScoreUpdater(ScorePanel) 
  # doInBackground() Void
}
class Simulation {
  + Simulation() 
  + run() void
}
class Soldier {
  + Soldier(String, int, int, Anthill) 
  + attack() void
  + run() void
}
class Stone {
  + Stone(String, int, double, double) 
}
class Updater {
  + Updater() 
}
class Vertex {
  + Vertex(String, int, double, double) 
  - double prep_y
  # Semaphore semaphore
  - int x
  # String name
  # ArrayList~RedAnt~ redAnts
  - double prep_x
  # ArrayList~BlueAnt~ blueAnts
  # int number_of_larvae
  - int y
  # ArrayList~Vertex~ neighbors
  + lookForRedEnemy() RedAnt
  + addLarvae(int) void
  + removeLarvae(int) void
  + addNeighbors(Vertex) void
  + removeRedAnt(RedAnt) void
  + lookForBlueEnemy() BlueAnt
  + calculate_x(JPanel) void
  + calculate_y(JPanel) void
  + addRedAnt(RedAnt) void
  + addBlueAnt(BlueAnt) void
  + removeBlueAnt(BlueAnt) void
   String name
   Semaphore semaphore
   double prep_y
   int y
   int x
   ArrayList~Vertex~ neighbors
   ArrayList~BlueAnt~ blueAnts
   double prep_x
   int number_of_larvae
   ArrayList~RedAnt~ redAnts
}
class VertexFactory {
  - VertexFactory(ArrayList~String~) 
  - int total_larvae
  + initVertex() Vertex
  - chooseRandomStats() void
  + getInstance(ArrayList~String~, Anthill, Anthill) VertexFactory
   int total_larvae
}
class Worker {
  + Worker(String, int, int, Anthill) 
  + collectLarvae() void
  + attack() void
  + run() void
}
class World {
  - World(int, double) 
  - int total_larvae
  - Anthill blueAnthill
  - ArrayList~String~ places
  - Anthill redAnthill
  - ArrayList~Vertex~ world
  - double density
  - int size
  + init(int, double) void
  + access() World
  + addVertex() void
   Anthill redAnthill
   ArrayList~String~ places
   ArrayList~Vertex~ world
   double density
   int size
   int total_larvae
   Anthill blueAnthill
}

AddAntButton "1" *--> "antPopulation 1" AntPopulation 
Ant "1" *--> "anthill 1" Anthill 
Ant  ..>  Dying 
Ant  ..>  Moving 
Ant  ..>  Returning 
Ant "1" *--> "currentVertex 1" Vertex 
AntFactory "1" *--> "red 1" Anthill 
AntGraph "1" *--> "antPopulation 1" AntPopulation 
AntGraph "1" *--> "points *" Pair 
AntGraph "1" *--> "points *" Vertex 
AntGraph "1" *--> "world 1" World 
AntPopulation "1" *--> "ants *" Ant 
AntPopulation "1" *--> "antFactory 1" AntFactory 
AntPopulation "1" *--> "blue_ants *" BlueAnt 
AntPopulation "1" *--> "red_ants *" RedAnt 
AntPopulation "1" *--> "world 1" World 
Anthill  -->  Vertex 
BlueAnt  -->  Ant 
BluePoints "1" *--> "blue 1" Anthill 
BluePoints  -->  Points 
Blunderer  -->  Collector 
Collector  ..>  Collecting 
Collector  -->  RedAnt 
DeadRemoval "1" *--> "antPopulation 1" AntPopulation 
Drone  -->  BlueAnt 
GraphUpdater "1" *--> "antGraph 1" AntGraph 
GraphUpdater  -->  Updater 
InfoPanel "1" *--> "antPopulation 1" AntPopulation 
InfoUpdater "1" *--> "infoPanel 1" InfoPanel 
InfoUpdater  -->  Updater 
Leaf  -->  Vertex 
MainFrame "1" *--> "graphUpdater 1" GraphUpdater 
MainFrame "1" *--> "infoUpdater 1" InfoUpdater 
MainFrame "1" *--> "scoreUpdater 1" ScoreUpdater 
Points "1" *--> "world 1" World 
RedAnt  -->  Ant 
RedPoints "1" *--> "red 1" Anthill 
RedPoints  -->  Points 
ScorePanel "1" *--> "bluePoints 1" BluePoints 
ScorePanel "1" *--> "redPoints 1" RedPoints 
ScoreUpdater "1" *--> "scorePanel 1" ScorePanel 
ScoreUpdater  -->  Updater 
Simulation "1" *--> "antPopulation 1" AntPopulation 
Simulation "1" *--> "mainFrame 1" MainFrame 
Soldier  ..>  Fighting 
Soldier  -->  RedAnt 
Stone  -->  Vertex 
Vertex "1" *--> "blueAnts *" BlueAnt 
Vertex "1" *--> "redAnts *" RedAnt 
Worker  -->  BlueAnt 
Worker  ..>  Collecting 
Worker  ..>  Fighting 
World "1" *--> "redAnthill 1" Anthill 
World "1" *--> "world *" Vertex 
World "1" *--> "vertexFactory 1" VertexFactory 
