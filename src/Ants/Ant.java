package Ants;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Main.Simulation;

import java.util.Random;
import java.util.Stack;

/**
 * This abstract class represents an Ant, which is a type of Thread.
 * It includes methods for moving, dying, storing larvae as food, and dropping larvae.
 * It also includes getter and setter methods for various properties of the Ant.
 */
abstract public class Ant extends Thread implements Returning, Moving, Dying
{
    protected static final String ANSI_RESET = "\u001B[0m";
    protected final String resource;
    protected String name;
    protected int strength;
    protected int health;
    protected String color;
    protected Stack<Vertex> path;
    protected Vertex currentVertex;
    protected Anthill anthill;
    protected int collected_larvae;
    protected volatile boolean alive;
    protected int sleep_time = Simulation.SLEEP_TIME;
    private int x;
    private int y;

    /**
     * Constructor for the Ant class.
     *
     * @param name     The name of the ant.
     * @param strength The strength of the ant.
     * @param health   The health of the ant.
     * @param color    The color of the ant.
     * @param anthill  The anthill the ant belongs to.
     */
    public Ant(String name, int strength, int health, String color, Anthill anthill)
    {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.color = color;
        path = new Stack<>();
        this.anthill = anthill;
        currentVertex = anthill;
        collected_larvae = 0;
        alive = true;
        x = new Random().nextInt(20) + 20;
        y = new Random().nextInt(20) + 20;
        if (Simulation.FREEDOM_MODE)
            resource = "dollars";
        else
            resource = "larvae";
    }

    /**
     * This method is used to move the ant to a random vertex.
     *
     * @throws InterruptedException If the thread is interrupted.
     */
    @Override
    public void randomMove() throws InterruptedException
    {
        Vertex next = currentVertex.getNeighbors().get((int) (Math.random() * currentVertex.getNeighbors().size()));
        if (next != anthill)
        {
            path.push(currentVertex);
        } else
        {
            path.clear();
        }
        move(next);
    }

    /**
     * This method is used to move the ant to a new vertex.
     *
     * @param v The vertex to move to.
     * @throws InterruptedException If the thread is interrupted.
     */
    @Override
    public void move(Vertex v) throws InterruptedException
    {
        currentVertex = v;
    }

    /**
     * This method is used to return the ant to the anthill.
     *
     * @throws InterruptedException If the thread is interrupted.
     */
    @Override
    public void returnToAnthill() throws InterruptedException
    {
        while (!path.empty())
        {
            Vertex v = path.pop();
            sleep(sleep_time / 2);
            move(v);
        }
        assert currentVertex == anthill;
        storeLarvaeAsFood();
    }

    /**
     * This method is used to drop a certain amount of larvae.
     *
     * @param amount The amount of larvae to drop.
     */
    public void dropLarvae(int amount)
    {
        collected_larvae -= amount;
        currentVertex.setNumber_of_larvae(currentVertex.getNumber_of_larvae() + amount);
    }

    /**
     * This method is used when the ant dies.
     */
    @Override
    public void die()
    {
        dropLarvae(collected_larvae);
        alive = false;
        this.interrupt();
    }

    /**
     * This method is used to receive damage.
     *
     * @param damage The amount of damage to receive.
     */
    @Override
    public void receiveDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            die();
        }
    }

    /**
     * This method is used to store larvae as food.
     */
    public void storeLarvaeAsFood()
    {
        anthill.addFood(collected_larvae);
        collected_larvae = 0;
    }

    /**
     * Returns the name of the ant.
     *
     * @return A string representing the name of the ant.
     */
    public String get_Name()
    {
        return name;
    }

    /**
     * Sets the name of the ant.
     *
     * @param name A string representing the new name of the ant.
     */
    public void set_Name(String name)
    {
        this.name = name;
    }

    /**
     * Returns the strength of the ant.
     *
     * @return An integer representing the strength of the ant.
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * Sets the strength of the ant.
     *
     * @param strength An integer representing the new strength of the ant.
     */
    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    /**
     * Returns the health of the ant.
     *
     * @return An integer representing the health of the ant.
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Sets the health of the ant.
     *
     * @param health An integer representing the new health of the ant.
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Returns the color of the ant.
     *
     * @return A string representing the color of the ant.
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Sets the color of the ant.
     *
     * @param color A string representing the new color of the ant.
     */
    public void setColor(String color)
    {
        this.color = color;
    }

    /**
     * Returns the path of the ant.
     *
     * @return A Stack of Vertex objects representing the path of the ant.
     */
    public Stack<Vertex> getPath()
    {
        return path;
    }

    /**
     * Sets the path of the ant.
     *
     * @param path A Stack of Vertex objects representing the new path of the ant.
     */
    public void setPath(Stack<Vertex> path)
    {
        this.path = path;
    }

    /**
     * Returns the current vertex of the ant.
     *
     * @return A Vertex object representing the current vertex of the ant.
     */
    public Vertex getCurrentVertex()
    {
        return currentVertex;
    }

    /**
     * Sets the current vertex of the ant.
     *
     * @param currentVertex A Vertex object representing the new current vertex of the ant.
     */
    public void setCurrentVertex(Vertex currentVertex)
    {
        this.currentVertex = currentVertex;
    }

    /**
     * Returns the anthill of the ant.
     *
     * @return An Anthill object representing the anthill of the ant.
     */
    public Anthill getAnthill()
    {
        return anthill;
    }

    /**
     * Sets the anthill of the ant.
     *
     * @param anthill An Anthill object representing the new anthill of the ant.
     */
    public void setAnthill(Anthill anthill)
    {
        this.anthill = anthill;
    }

    /**
     * Returns the number of larvae collected by the ant.
     *
     * @return An integer representing the number of larvae collected by the ant.
     */
    public int getCollected_larvae()
    {
        return collected_larvae;
    }

    /**
     * Sets the number of larvae collected by the ant.
     *
     * @param collected_larvae An integer representing the new number of larvae collected by the ant.
     */
    public void setCollected_larvae(int collected_larvae)
    {
        this.collected_larvae = collected_larvae;
    }

    /**
     * Returns the alive status of the ant.
     *
     * @return A boolean representing whether the ant is alive.
     */
    public boolean getAlive()
    {
        return alive;
    }

    /**
     * Sets the alive status of the ant.
     *
     * @param alive A boolean representing the new alive status of the ant.
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    /**
     * Returns the x-coordinate of the ant.
     *
     * @return An integer representing the x-coordinate of the ant.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Sets the x-coordinate of the ant.
     *
     * @param x An integer representing the new x-coordinate of the ant.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the ant.
     *
     * @return An integer representing the y-coordinate of the ant.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the y-coordinate of the ant.
     *
     * @param y An integer representing the new y-coordinate of the ant.
     */
    public void setY(int y)
    {
        this.y = y;
    }
}
