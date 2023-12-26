import AntWorld.Anthill;
import AntWorld.Vertex;
import Ants.Worker;

public class Main {
    public static void main(String[] args) {
        Anthill anthill = new Anthill();
        Vertex vertex = new Vertex(2);
        anthill.addNeighbors(vertex);
        vertex.addNeighbors(anthill);
        Worker worker = new Worker("work", 12, 12, anthill);
        worker.randomMove();
        worker.collectLarvae();
    }
}