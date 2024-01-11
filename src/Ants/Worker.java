package Ants;

import AntWorld.Anthill;
import Main.Simulation;

/**
 * Worker class extends BlueAnt and implements Fighting and Collecting interfaces.
 * This class represents a worker ant in the simulation.
 * A worker ant functions both as a collector of larvae and a fighter.
 */
public class Worker extends BlueAnt implements Fighting, Collecting
{

    /**
     * Constructor for the Worker class.
     *
     * @param name     Name of the worker ant.
     * @param strength Strength of the worker ant.
     * @param health   Health of the worker ant.
     * @param anthill  Anthill the worker ant belongs to.
     */
    public Worker(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    /**
     * Method to collect larvae.
     * The worker ant collects larvae if there are any in the current vertex.
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
            returnToAnthill();
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " found no %s\n", name, resource);
            currentVertex.getSemaphore().release();
        }

    }

    /**
     * Method to attack.
     * The worker ant attacks a red ant enemy if there is one in the current vertex.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void attack() throws InterruptedException
    {
        currentVertex.getSemaphore().acquire();
        RedAnt enemy = currentVertex.lookForRedEnemy();
        if (enemy != null)
        {
            if (Simulation.VERBOSITY >= 2)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is attacking %s\n", name, enemy.get_Name());
            enemy.receiveDamage(strength);
            currentVertex.getSemaphore().release();
            returnToAnthill();
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s " + ANSI_RESET + " found no enemy\n", name);
            currentVertex.getSemaphore().release();
        }

    }

    /**
     * Run method for the worker ant.
     * The worker ant moves randomly, sleeps, attacks, collects larvae, and sleeps again in a loop while it is alive.
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
                attack();
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