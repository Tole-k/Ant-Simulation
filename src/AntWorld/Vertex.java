package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Vertex
{
    protected final ArrayList<RedAnt> redAnts;
    protected final ArrayList<BlueAnt> blueAnts;
    private final double prep_x;
    private final double prep_y;
    protected volatile Semaphore semaphore = new Semaphore(1);
    protected int number_of_larvae;
    protected ArrayList<Vertex> neighbors;
    protected String name;
    private int x = -1;
    private int y = -1;

    public Vertex(String name, int number_of_larvae, double prep_x, double prep_y)
    {
        this.name = name;
        this.number_of_larvae = number_of_larvae;
        this.neighbors = new ArrayList<>();
        this.redAnts = new ArrayList<>();
        this.blueAnts = new ArrayList<>();
        this.prep_x = prep_x;
        this.prep_y = prep_y;
    }

    public RedAnt lookForRedEnemy()
    {
        if (redAnts.isEmpty())
        {
            return null;
        }
        return redAnts.get(0);

    }

    public BlueAnt lookForBlueEnemy()
    {
        if (blueAnts.isEmpty())
        {
            return null;
        }
        return blueAnts.get(0);
    }

    public void calculate_x(JPanel panel)
    {
        x = (int) (prep_x * panel.getWidth());
    }

    public void calculate_y(JPanel panel)
    {
        y = (int) (prep_y * panel.getHeight());
    }

    public int getNumber_of_larvae()
    {
        return number_of_larvae;
    }

    public void setNumber_of_larvae(int number_of_larvae)
    {
        this.number_of_larvae = number_of_larvae;
    }

    public void addLarvae(int amount)
    {
        number_of_larvae += amount;
    }

    public void removeLarvae(int amount)
    {
        number_of_larvae -= amount;
    }

    public ArrayList<RedAnt> getRedAnts()
    {
        return redAnts;
    }

    public ArrayList<BlueAnt> getBlueAnts()
    {
        return blueAnts;
    }

    public ArrayList<Vertex> getNeighbors()
    {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Vertex> neighbors)
    {
        this.neighbors = neighbors;
    }

    public void addNeighbors(Vertex neighbour)
    {
        neighbors.add(neighbour);
    }

    public void addRedAnt(RedAnt ant)
    {
        redAnts.add(ant);
    }

    public void removeRedAnt(RedAnt ant)
    {
        redAnts.remove(ant);
    }

    public void addBlueAnt(BlueAnt ant)
    {
        blueAnts.add(ant);
    }

    public void removeBlueAnt(BlueAnt ant)
    {
        blueAnts.remove(ant);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrep_x()
    {
        return prep_x;
    }

    public double getPrep_y()
    {
        return prep_y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Semaphore getSemaphore()
    {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore)
    {
        this.semaphore = semaphore;
    }
}
