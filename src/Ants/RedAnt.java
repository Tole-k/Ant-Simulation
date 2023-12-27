package Ants;

import AntWorld.Anthill;
import AntWorld.Stone;
import AntWorld.Vertex;

abstract public class RedAnt extends Ant {

    public RedAnt(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, "red", anthill);
        currentVertex.addRedAnt(this);
    }

    @Override
    public void move(Vertex v) throws InterruptedException {
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.removeRedAnt(this);
        currentVertex.semaphore.release();
        super.move(v);
        currentVertex.semaphore.acquireUninterruptibly();
        currentVertex.addRedAnt(this);
        currentVertex.semaphore.release();
        if (currentVertex instanceof Stone) {
            sleep(1000);
        }
    }
}
