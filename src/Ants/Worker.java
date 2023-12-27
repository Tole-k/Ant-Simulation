package Ants;

import AntWorld.Anthill;

public class Worker extends BlueAnt implements Fighting, Collecting
{

    public Worker(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    @Override
    public void collectLarvae() throws InterruptedException
    {
        currentVertex.semaphore.acquire();
        if (currentVertex.getNumber_of_larvae() > 0)
        {
            currentVertex.removeLarvae(1);
            collected_larvae += 1;
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " collected a larvae\n", name);
            currentVertex.semaphore.release();
            returnToAnthill();
        } else
        {
            //System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " found no larvae\n", name);
            currentVertex.semaphore.release();
        }

    }

    @Override
    public void attack() throws InterruptedException
    {
        currentVertex.semaphore.acquire();
        RedAnt enemy = currentVertex.lookForRedEnemy();
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
            try
            {
                collectLarvae();
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
