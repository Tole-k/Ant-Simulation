package Ants;

import AntWorld.Anthill;
import Main.Simulation;

public class Worker extends BlueAnt implements Fighting, Collecting
{

    public Worker(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

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
            //System.out.println("Returning to anthill...");
            returnToAnthill();
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s " + ANSI_RESET + " found no enemy\n", name);
            currentVertex.getSemaphore().release();
        }

    }

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
