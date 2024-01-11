package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

/**
 * Abstract RedAnt class extends Ant.
 * This class represents a red ant in the simulation.
 */
abstract public class RedAnt extends Ant
{
    /**
     * ANSI color code for red.
     */
    protected static final String ANSI_COLOR = "\u001B[31m";

    /**
     * Constructor for the RedAnt class.
     *
     * @param name     Name of the red ant.
     * @param strength Strength of the red ant.
     * @param health   Health of the red ant.
     * @param anthill  Anthill the red ant belongs to.
     */
    public RedAnt(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, "red", anthill);
        currentVertex.addRedAnt(this);
    }

    /**
     * Method to move the ant to a new vertex.
     * The ant removes itself from the current vertex, moves to the new vertex, and adds itself to the new vertex.
     * If the new vertex is a stone, the ant sleeps for a longer time.
     *
     * @param v The new vertex to move to.
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void move(Vertex v) throws InterruptedException
    {
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.removeRedAnt(this);
        currentVertex.getSemaphore().release();
        if (Simulation.VERBOSITY >= 3)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is moving from %s to %s%n", name, currentVertex.getName(), v.getName());
        super.move(v);
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.addRedAnt(this);
        currentVertex.getSemaphore().release();
        if (currentVertex instanceof Stone)
        {
            sleep(sleep_time * 5L);
        }
    }

    /**
     * This method is used to make the ant die.
     * It removes the ant from the current vertex and sets the alive boolean to false.
     */
    @Override
    public void die()
    {
        currentVertex.removeRedAnt(this);
        if (Simulation.VERBOSITY >= 1)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " died\n", name);
        super.die();
    }

    /**
     * Method to store collected larvae in anthill as food.
     * If the ant has collected any larvae, it stores them as food.
     */
    @Override
    public void storeLarvaeAsFood()
    {
        if (collected_larvae > 0)
        {
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + "stored %d %s in base. ", name, collected_larvae, resource);
            super.storeLarvaeAsFood();
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " has %d %s stored\n", anthill.getName(), anthill.getAmount_of_food(), resource);
        }
    }

    /**
     * Method to drop larvae.
     *
     * @param amount The amount of larvae to drop.
     */
    @Override
    public void dropLarvae(int amount)
    {
        if (amount > 0)
        {
            super.dropLarvae(amount);
            if (Simulation.VERBOSITY >= 2)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " dropped %d %s\n", name, amount, resource);
        }
    }
}