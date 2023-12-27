package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

public class Vertex {
    public volatile Semaphore semaphore = new Semaphore(1);
    protected int number_of_larvae;
    protected ArrayList<RedAnt> redAnts;
    protected ArrayList<BlueAnt> blueAnts;
    protected ArrayList<Vertex> neighbors;

    protected String name;

    public Vertex(String name, int number_of_larvae) {
        this.name = name;
        this.number_of_larvae = number_of_larvae;
        this.neighbors = new ArrayList<>();
        this.redAnts = new ArrayList<>();
        this.blueAnts = new ArrayList<>();
    }

    public RedAnt lookForRedEnemy() {
        try {
            return redAnts.getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public BlueAnt lookForBlueEnemy() {
        try {
            return blueAnts.getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int getNumber_of_larvae() {
        return number_of_larvae;
    }

    public void setNumber_of_larvae(int number_of_larvae) {
        this.number_of_larvae = number_of_larvae;
    }

    public void addLarvae(int amount) {
        number_of_larvae += amount;
    }

    public void removeLarvae(int amount) {
        number_of_larvae -= amount;
    }

    public ArrayList<RedAnt> getRedAnts() {
        return redAnts;
    }

    public ArrayList<BlueAnt> getBlueAnts() {
        return blueAnts;
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

    public void addRedAnt(RedAnt ant) {
        redAnts.add(ant);
    }

    public void removeRedAnt(RedAnt ant) {
        redAnts.remove(ant);
    }

    public void addBlueAnt(BlueAnt ant) {
        blueAnts.add(ant);
    }

    public void removeBlueAnt(BlueAnt ant) {
        blueAnts.remove(ant);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
