package Ants;

import AntWorld.Anthill;
import AntWorld.Vertex;

import java.util.Random;
import java.util.Stack;

abstract public class Ant extends Thread implements Returning, Moving, Dying
{
    protected static final String ANSI_RESET = "\u001B[0m";
    protected String name;
    protected int strength;
    protected int health;
    protected String color;
    protected Stack<Vertex> path;
    protected Vertex currentVertex;
    protected Anthill anthill;
    protected int collected_larvae;
    protected volatile boolean alive;
    private int x;
    private int y;

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
    }

    @Override
    public void randomMove() throws InterruptedException
    {
        Vertex next = currentVertex.getNeighbors().get((int) (Math.random() * currentVertex.getNeighbors().size()));
        if (next != anthill)
        {
            path.push(currentVertex);
        }
        path.clear();
        move(next);
    }

    @Override
    public void move(Vertex v) throws InterruptedException
    {
        currentVertex = v;
    }

    @Override
    public void returnToAnthill() throws InterruptedException
    {
        while (!path.empty())
        {
            Vertex v = path.pop();
            move(v);
        }
        assert currentVertex == anthill;
        storeLarvaeAsFood();
    }

    public void dropLarvae(int amount)
    {
        collected_larvae -= amount;
        currentVertex.setNumber_of_larvae(currentVertex.getNumber_of_larvae() + amount);
    }

    @Override
    public void die()
    {
        dropLarvae(collected_larvae);
        alive = false;
        this.interrupt();
    }

    @Override
    public void receiveDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            die();
        }
    }

    public void storeLarvaeAsFood()
    {
        anthill.addFood(collected_larvae);
        collected_larvae = 0;
    }

    public String get_Name()
    {
        return name;
    }

    public void set_Name(String name)
    {
        this.name = name;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Stack<Vertex> getPath()
    {
        return path;
    }

    public void setPath(Stack<Vertex> path)
    {
        this.path = path;
    }

    public Vertex getCurrentVertex()
    {
        return currentVertex;
    }

    public void setCurrentVertex(Vertex currentVertex)
    {
        this.currentVertex = currentVertex;
    }

    public Anthill getAnthill()
    {
        return anthill;
    }

    public void setAnthill(Anthill anthill)
    {
        this.anthill = anthill;
    }

    public int getCollected_larvae()
    {
        return collected_larvae;
    }

    public void setCollected_larvae(int collected_larvae)
    {
        this.collected_larvae = collected_larvae;
    }

    public boolean getAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
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
}
