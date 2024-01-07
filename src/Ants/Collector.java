package Ants;

import AntWorld.Anthill;
import Main.Simulation;

/**
 * Collector class extends RedAnt and implements Collecting interface.
 * This class represents a collector ant in the simulation.
 */
public class Collector extends RedAnt implements Collecting
{

    /**
     * Constructor for the Collector class.
     *
     * @param name     Name of the collector ant.
     * @param strength Strength of the collector ant.
     * @param health   Health of the collector ant.
     * @param anthill  Anthill the collector ant belongs to.
     */
    public Collector(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    /**
     * Method to collect larvae.
     * The collector ant collects larvae if there are any in the current vertex.
     * If the number of collected larvae is greater than or equal to the ant's strength, the ant returns to the anthill.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void collectLarvae() throws InterruptedException
    {
        currentVertex.getSemaphore().acquire();
        if (currentVertex.getNumber_of_larvae() > 0)
        {
            int amount = currentVertex.getNumber_of_larvae();
            currentVertex.removeLarvae(amount);
            collected_larvae += amount;
            if (Simulation.VERBOSITY >= 2)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " collected %d %s\n", name, amount, resource);
            currentVertex.getSemaphore().release();
            if (collected_larvae >= strength)
            {
                returnToAnthill();
            }
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " found no %s\n", name, resource);
            currentVertex.getSemaphore().release();
        }

    }

    /**
     * Run method for the collector ant.
     * The collector ant moves randomly, sleeps, collects larvae, and sleeps again in a loop while it is alive.
     */
    @Override
    public void run()
    {
        while (alive)
        {
            try
            {
                randomMove();
            } catch (InterruptedException e)
            {
                break;
            }
            try
            {
                sleep(sleep_time);
            } catch (InterruptedException e)
            {
                break;
            }
            try
            {
                collectLarvae();
            } catch (InterruptedException e)
            {
                break;
            }
            try
            {
                sleep(sleep_time);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}