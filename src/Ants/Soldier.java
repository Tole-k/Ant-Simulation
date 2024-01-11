package Ants;

import AntWorld.Anthill;
import Main.Simulation;

/**
 * Soldier class extends RedAnt and implements Fighting interface.
 * This class represents a soldier ant in the simulation.
 * A soldier ant is a red ant that can attack blue ants.
 */
public class Soldier extends RedAnt implements Fighting
{
    /**
     * Constructor for the Soldier class.
     *
     * @param name     Name of the soldier ant.
     * @param strength Strength of the soldier ant.
     * @param health   Health of the soldier ant.
     * @param anthill  Anthill the soldier ant belongs to.
     */
    public Soldier(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    /**
     * Method to attack.
     * The soldier ant attacks a blue ant enemy if there is one in the current vertex.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void attack() throws InterruptedException
    {
        currentVertex.getSemaphore().acquire();
        BlueAnt enemy = currentVertex.lookForBlueEnemy();
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
     * Run method for the soldier ant.
     * The soldier ant moves randomly, sleeps, attacks, and sleeps again in a loop while it is alive.
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
        }
    }

}