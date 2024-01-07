package Ants;

import AntWorld.Anthill;
import AntWorld.Vertex;

/**
 * Blunderer class extends Collector.
 * This class represents a blunderer ant in the simulation.
 * Blunderer ants have a chance to drop larvae while returning to the anthill.
 */
public class Blunderer extends Collector
{
    /**
     * Chance for the blunderer ant to drop larvae.
     */
    private static final double dropChance = 0.25;

    /**
     * Constructor for the Blunderer class.
     *
     * @param name     Name of the blunderer ant.
     * @param strength Strength of the blunderer ant.
     * @param health   Health of the blunderer ant.
     * @param anthill  Anthill the blunderer ant belongs to.
     */
    public Blunderer(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    /**
     * Method to get the drop chance of the blunderer ant.
     *
     * @return The drop chance of the blunderer ant.
     */
    public static double getDropChance()
    {
        return dropChance;
    }

    /**
     * Method for the blunderer ant to return to the anthill.
     * The blunderer ant moves to each vertex in its path, sleeping between each move.
     * There is a chance for the ant to drop larvae at each vertex.
     * Once the ant reaches the anthill, it stores any remaining larvae as food.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void returnToAnthill() throws InterruptedException
    {
        while (!path.empty())
        {
            Vertex v = path.pop();
            sleep(sleep_time);
            move(v);
            if (Math.random() < dropChance)
            {
                currentVertex.getSemaphore().acquire();
                dropLarvae(1);
                currentVertex.getSemaphore().release();
            }
        }
        assert currentVertex == anthill;
        storeLarvaeAsFood();
    }
}