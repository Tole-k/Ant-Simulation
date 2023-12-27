package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;

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
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.removeRedAnt(this);
        currentVertex.semaphore.release();
        //System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " is moving from %s to %s%n", name, currentVertex.getName(), v.getName());
        super.move(v);
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.addRedAnt(this);
        currentVertex.semaphore.release();
        if (currentVertex instanceof Stone)
        {
            sleep(1000);
        }
    }

    @Override
    public void die()
    {
        currentVertex.removeRedAnt(this);
        System.out.printf(ANSI_COLOR + "%s" + ANSI_RESET + " died\n", name);
        super.die();
    }
}
