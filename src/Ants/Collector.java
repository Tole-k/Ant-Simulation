package Ants;

import AntWorld.Anthill;
import Main.Simulation;

public class Collector extends RedAnt implements Collecting
{

    public Collector(String name, int strength, int health, Anthill anthill)
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
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " collected %d larvae\n", name, amount);
            currentVertex.getSemaphore().release();
            if (collected_larvae >= strength)
            {
                returnToAnthill();
            }
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " found no larvae\n", name);
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
                sleep(Simulation.SLEEP_TIME);
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
                sleep(Simulation.SLEEP_TIME);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
