package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

abstract public class BlueAnt extends Ant
{
    protected static final String ANSI_COLOR = "\u001B[34m";

    public BlueAnt(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, "blue", anthill);
        currentVertex.addBlueAnt(this);
    }

    @Override
    public void move(Vertex v) throws InterruptedException
    {
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.removeBlueAnt(this);
        currentVertex.semaphore.release();
        if (Simulation.verbosity >= 3)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is moving from %s to %s%n", name, currentVertex.getName(), v.getName());
        super.move(v);
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.addBlueAnt(this);
        currentVertex.semaphore.release();
        if (currentVertex instanceof Stone)
        {
            sleep(1000);
        }
    }

    @Override
    public void die()
    {
        currentVertex.removeBlueAnt(this);
        if (Simulation.verbosity >= 1)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " died\n", name);
        super.die();
    }

    @Override
    public void storeLarvaeAsFood()
    {
        if (collected_larvae > 0)
        {
            if (Simulation.verbosity >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " stored %d food in anthill. ", name, collected_larvae);
            super.storeLarvaeAsFood();
            if (Simulation.verbosity >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " has %d food stored\n", anthill.getName(), anthill.getAmount_of_food());
        }
    }
}
