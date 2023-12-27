import AntWorld.Anthill;
import AntWorld.Vertex;
import Ants.Soldier;
import Ants.Worker;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Anthill blueAnthill = new Anthill("blueBase");
        Anthill redAnthill = new Anthill("redBase");
        Vertex vertex = new Vertex("Battleground", 2);
        blueAnthill.addNeighbors(vertex);
        redAnthill.addNeighbors(vertex);
        vertex.addNeighbors(blueAnthill);
        vertex.addNeighbors(redAnthill);
        Worker worker = new Worker("worker", 4, 12, blueAnthill);
        Soldier soldier = new Soldier("soldier", 4, 12, redAnthill);
        worker.start();
        soldier.start();
        soldier.join();
        worker.join();
    }
}