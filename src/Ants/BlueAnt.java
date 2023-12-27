package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;

abstract public class BlueAnt extends Ant {

    public BlueAnt(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, "blue", anthill);
        currentVertex.addBlueAnt(this);
    }

    @Override
    public void move(Vertex v) throws InterruptedException {
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.removeBlueAnt(this);
        currentVertex.semaphore.release();
        super.move(v);
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.addBlueAnt(this);
        currentVertex.semaphore.release();
        if (currentVertex instanceof Stone) {
            sleep(1000);
        }
    }
}
