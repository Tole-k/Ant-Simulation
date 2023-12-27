package Ants;

import AntWorld.Anthill;

public class Soldier extends RedAnt implements Fighting
{
    public Soldier(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    @Override
    public void attack() throws InterruptedException
    {
        currentVertex.semaphore.acquire();
        BlueAnt enemy = currentVertex.lookForBlueEnemy();
        if (enemy != null)
        {
            //System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + "is attacking %s\n", name, enemy.get_Name());
            enemy.receiveDamage(strength);
            currentVertex.semaphore.release();
            returnToAnthill();
        } else
        {
            //System.out.printf(ANSI_COLOR + "%s " + ANSI_RESET + " found no enemy\n", name);
            currentVertex.semaphore.release();
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
                sleep(SLEEP_TIME);
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
                sleep(SLEEP_TIME);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }

}
