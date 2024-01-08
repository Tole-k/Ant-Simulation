package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

/**
 * This abstract class represents a BlueAnt, which is a type of Ant.
 * It includes methods for moving, dying, storing larvae as food, and dropping larvae.
 */
abstract public class BlueAnt extends Ant
{
    protected static final String ANSI_COLOR = "\u001B[34m";

    /**
     * Constructor for the BlueAnt class.
     *
     * @param name     The name of the ant.
     * @param strength The strength of the ant.
     * @param health   The health of the ant.
     * @param anthill  The anthill the ant belongs to.
     */
    public BlueAnt(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, "blue", anthill);
        currentVertex.addBlueAnt(this);
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
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.removeBlueAnt(this);
        currentVertex.getSemaphore().release();
        if (Simulation.VERBOSITY >= 3)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is moving from %s to %s%n", name, currentVertex.getName(), v.getName());
        super.move(v);
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.addBlueAnt(this);
        currentVertex.getSemaphore().release();
        if (currentVertex instanceof Stone)
        {
            sleep(sleep_time * 5L);
        }
    }

    /**
     * This method is used when the ant dies.
     */
    @Override
    public void die()
    {
        currentVertex.removeBlueAnt(this);
        if (Simulation.VERBOSITY >= 1)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " died\n", name);
        super.die();
    }

    /**
     * This method is used to store larvae as food.
     */
    @Override
    public void storeLarvaeAsFood()
    {
        if (collected_larvae > 0)
        {
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " stored %d %s in base. ", name, collected_larvae, resource);
            super.storeLarvaeAsFood();
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " has %d %s stored\n", anthill.getName(), anthill.getAmount_of_food(), resource);
        }
    }

    /**
     * This method is used to drop a certain amount of larvae.
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