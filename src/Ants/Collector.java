package Ants;

import AntWorld.Anthill;

public class Collector extends RedAnt implements Collecting {

    public Collector(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void collectLarvae() throws InterruptedException {
        currentVertex.semaphore.acquire();
        if (currentVertex.getNumber_of_larvae() > 0) {
            currentVertex.removeLarvae(1);
            collected_larvae += 1;
            System.out.printf("%s, a %s ant collected a larvae\n", name, color);
            currentVertex.semaphore.release();
            if (collected_larvae >= strength) {
                returnToAnthill();
            }
        } else {
            System.out.printf("%s, a %s ant found no larvae\n", name, color);
            currentVertex.semaphore.release();
        }

    }

    @Override
    public void run() {
        while (alive) {
            try {
                randomMove();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                collectLarvae();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
