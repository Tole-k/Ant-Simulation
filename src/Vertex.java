import java.util.ArrayList;

public class Vertex {
    protected int number_of_larvae;
    protected ArrayList<Ant> ants;
    protected ArrayList<Vertex> neighbors;

    public Vertex(int number_of_larvae) {
        this.number_of_larvae = number_of_larvae;
        this.neighbors = new ArrayList<>();
        this.ants = new ArrayList<>();
    }

    public int getNumber_of_larvae() {
        return number_of_larvae;
    }

    public void setNumber_of_larvae(int number_of_larvae) {
        this.number_of_larvae = number_of_larvae;
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    public ArrayList<Vertex> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Vertex> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbors(Vertex neighbour) {
        neighbors.add(neighbour);
    }

    public void addAnt(Ant ant) {
        ants.add(ant);
    }

    public void removeAnt(Ant ant) {
        ants.remove(ant);
    }
}
