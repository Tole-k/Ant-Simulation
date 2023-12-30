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
        currentVertex.getRedAttackReadLock().lock();
        currentVertex.getCollectSemaphore().acquire();
        if (currentVertex.getNumber_of_larvae() > 0)
        {
            int amount = currentVertex.getNumber_of_larvae();
            currentVertex.removeLarvae(amount);
            collected_larvae += amount;
            if (Simulation.VERBOSITY >= 2)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " collected %d larvae\n", name, amount);
            currentVertex.getCollectSemaphore().release();
            currentVertex.getRedAttackReadLock().unlock();
            //System.out.println("Returning to anthill...");
            returnToAnthill();
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " found no larvae\n", name);
            currentVertex.getCollectSemaphore().release();
            currentVertex.getRedAttackReadLock().unlock();
        }

    }

    @Override
    public void attack() throws InterruptedException
    {
        currentVertex.getRedAttackReadLock().lock();
        currentVertex.getBlueAttackWriteLock().lock();
        RedAnt enemy = currentVertex.lookForRedEnemy();
        if (enemy != null)
        {
            if (Simulation.VERBOSITY >= 2)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is attacking %s\n", name, enemy.get_Name());
            enemy.receiveDamage(strength);
            currentVertex.getBlueAttackWriteLock().unlock();
            currentVertex.getRedAttackReadLock().unlock();
            //System.out.println("Returning to anthill...");
            returnToAnthill();
        } else
        {
            if (Simulation.VERBOSITY >= 3)
                System.out.printf(ANSI_COLOR + "%s " + ANSI_RESET + " found no enemy\n", name);
            currentVertex.getBlueAttackWriteLock().unlock();
            currentVertex.getRedAttackReadLock().unlock();
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
