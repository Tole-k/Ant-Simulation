package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

abstract public class RedAnt extends Ant
{
    protected static final String ANSI_COLOR = "\u001B[31m";

    public RedAnt(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, "red", anthill);
        currentVertex.addRedAnt(this);
    }

    @Override
    public void move(Vertex v) throws InterruptedException
    {
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.removeRedAnt(this);
        currentVertex.getSemaphore().release();
        if (Simulation.VERBOSITY >= 3)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is moving from %s to %s%n", name, currentVertex.getName(), v.getName());
        super.move(v);
        currentVertex.getSemaphore().acquireUninterruptibly();
        currentVertex.addRedAnt(this);
        currentVertex.getSemaphore().release();
        if (currentVertex instanceof Stone)
        {
            sleep(Simulation.SLEEP_TIME * 10L);
        }
    }

    @Override
    public void die()
    {
        currentVertex.removeRedAnt(this);
        System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " died\n", name);
        super.die();
    }

    @Override
    public void storeLarvaeAsFood()
    {
        if (collected_larvae > 0)
        {
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + "stored %d food in anthill. ", name, collected_larvae);
            super.storeLarvaeAsFood();
            if (Simulation.VERBOSITY >= 1)
                System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " has %d food stored\n", anthill.getName(), anthill.getAmount_of_food());
        }
    }

    @Override
    public void dropLarvae(int amount)
    {
        super.dropLarvae(amount);
        if (Simulation.VERBOSITY >= 0)
            System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " dropped %d larvae\n", name, amount);
    }
}
