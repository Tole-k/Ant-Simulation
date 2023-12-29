package Ants;

import AntWorld.Anthill;
import Main.Simulation;

public class Soldier extends RedAnt implements Fighting
{
    public Soldier(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

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
                attack();
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
