package Ants;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Main.Simulation;

public class Blunderer extends Collector
{
    private static final double dropChance = 0.25;

    public Blunderer(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    public static double getDropChance()
    {
        return dropChance;
    }

    @Override
    public void returnToAnthill() throws InterruptedException
    {
        while (!path.empty())
        {
            Vertex v = path.pop();
            sleep(Simulation.SLEEP_TIME);
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
