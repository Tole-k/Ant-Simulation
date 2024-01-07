package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
/**
 * This class represents a vertex in the ant world.
 * It contains lists of red and blue ants, a list of neighboring vertices, and attributes for the number of larvae, name, and location.
 * It also contains a semaphore for thread synchronization.
 */
public class Vertex
{
    // Lists of red and blue ants at the vertex.
    protected final ArrayList<RedAnt> redAnts;
    protected final ArrayList<BlueAnt> blueAnts;
    // The x and y coordinates of the vertex, preprocessed.
    private final double prep_x;
    private final double prep_y;
    // Semaphore for thread synchronization.
    protected volatile Semaphore semaphore = new Semaphore(1);
    // The number of larvae at the vertex.
    protected int number_of_larvae;
    // List of neighboring vertices.
    protected ArrayList<Vertex> neighbors;
    // The name of the vertex.
    protected String name;
    // The x and y coordinates of the vertex.
    private int x = -1;
    private int y = -1;
    /**
     * The constructor for the Vertex class.
     * It initializes the vertex with a name, number of larvae, and location (prep_x, prep_y).
     * It also initializes the lists of red ants, blue ants, and neighbors.
     *
     * @param name The name of the vertex.
     * @param number_of_larvae The number of larvae at the vertex.
     * @param prep_x The preprocessed x-coordinate of the vertex's location.
     * @param prep_y The preprocessed y-coordinate of the vertex's location.
     */
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
    /**
     * This method looks for a red enemy at the vertex.
     * If there are no red ants at the vertex, it returns null.
     * Otherwise, it returns the first red ant in the list.
     *
     * @return The first red ant in the list, or null if there are no red ants.
     */
    public RedAnt lookForRedEnemy()
    {
        if (redAnts.isEmpty())
        {
            return null;
        }
        return redAnts.get(0);

    }
    /**
     * This method looks for a blue enemy at the vertex.
     * If there are no blue ants at the vertex, it returns null.
     * Otherwise, it returns the first blue ant in the list.
     *
     * @return The first blue ant in the list, or null if there are no blue ants.
     */
    public BlueAnt lookForBlueEnemy()
    {
        if (blueAnts.isEmpty())
        {
            return null;
        }
        return blueAnts.get(0);
    }

    /**
     * This method calculates the x-coordinate of the vertex based on the size of the panel.
     *
     * @param panel The panel in which the vertex is located.
     */
    public void calculate_x(JPanel panel)
    {
        x = (int) (prep_x * 0.9 * panel.getWidth());
    }

    /**
     * This method calculates the y-coordinate of the vertex based on the size of the panel.
     *
     * @param panel The panel in which the vertex is located.
     */
    public void calculate_y(JPanel panel)
    {
        y = (int) (prep_y * 0.9 * panel.getHeight());
    }

    /**
     * This method returns the number of larvae at the vertex.
     *
     * @return The number of larvae.
     */
    public int getNumber_of_larvae()
    {
        return number_of_larvae;
    }

    /**
     * This method sets the number of larvae at the vertex.
     *
     * @param number_of_larvae The number of larvae to be set.
     */
    public void setNumber_of_larvae(int number_of_larvae)
    {
        this.number_of_larvae = number_of_larvae;
    }

    /**
     * This method adds a specified amount of larvae to the vertex.
     *
     * @param amount The amount of larvae to be added.
     */
    public void addLarvae(int amount)
    {
        number_of_larvae += amount;
    }

    /**
     * This method removes a specified amount of larvae from the vertex.
     *
     * @param amount The amount of larvae to be removed.
     */
    public void removeLarvae(int amount)
    {
        number_of_larvae -= amount;
    }

    /**
     * This method returns the list of red ants at the vertex.
     *
     * @return The list of red ants.
     */
    public ArrayList<RedAnt> getRedAnts()
    {
        return redAnts;
    }

    /**
     * This method returns the list of blue ants at the vertex.
     *
     * @return The list of blue ants.
     */
    public ArrayList<BlueAnt> getBlueAnts()
    {
        return blueAnts;
    }

    /**
     * This method returns the list of neighboring vertices.
     *
     * @return The list of neighboring vertices.
     */
    public ArrayList<Vertex> getNeighbors()
    {
        return neighbors;
    }

    /**
     * This method sets the list of neighboring vertices.
     *
     * @param neighbors The list of neighboring vertices to be set.
     */
    public void setNeighbors(ArrayList<Vertex> neighbors)
    {
        this.neighbors = neighbors;
    }

    /**
     * This method adds a vertex to the list of neighbors.
     *
     * @param neighbour The vertex to be added to the list of neighbors.
     */
    public void addNeighbors(Vertex neighbour)
    {
        neighbors.add(neighbour);
    }

    /**
     * This method adds a red ant to the list of red ants.
     *
     * @param ant The red ant to be added.
     */
    public void addRedAnt(RedAnt ant)
    {
        redAnts.add(ant);
    }

    /**
     * This method removes a red ant from the list of red ants.
     *
     * @param ant The red ant to be removed.
     */
    public void removeRedAnt(RedAnt ant)
    {
        redAnts.remove(ant);
    }

    /**
     * This method adds a blue ant to the list of blue ants.
     *
     * @param ant The blue ant to be added.
     */
    public void addBlueAnt(BlueAnt ant)
    {
        blueAnts.add(ant);
    }

    /**
     * This method removes a blue ant from the list of blue ants.
     *
     * @param ant The blue ant to be removed.
     */
    public void removeBlueAnt(BlueAnt ant)
    {
        blueAnts.remove(ant);
    }

    /**
     * This method returns the name of the vertex.
     *
     * @return The name of the vertex.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method sets the name of the vertex.
     *
     * @param name The name to be set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method returns the preprocessed x-coordinate of the vertex.
     *
     * @return The preprocessed x-coordinate.
     */
    public double getPrep_x()
    {
        return prep_x;
    }

    /**
     * This method returns the preprocessed y-coordinate of the vertex.
     *
     * @return The preprocessed y-coordinate.
     */
    public double getPrep_y()
    {
        return prep_y;
    }

    /**
     * This method returns the x-coordinate of the vertex.
     *
     * @return The x-coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * This method sets the x-coordinate of the vertex.
     *
     * @param x The x-coordinate to be set.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * This method returns the y-coordinate of the vertex.
     *
     * @return The y-coordinate.
     */
    public int getY()
    {
        return y;
    }

    /**
     * This method sets the y-coordinate of the vertex.
     *
     * @param y The y-coordinate to be set.
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * This method returns the semaphore of the vertex.
     *
     * @return The semaphore.
     */
    public Semaphore getSemaphore()
    {
        return semaphore;
    }

    /**
     * This method sets the semaphore of the vertex.
     *
     * @param semaphore The semaphore to be set.
     */
    public void setSemaphore(Semaphore semaphore)
    {
        this.semaphore = semaphore;
    }
}
