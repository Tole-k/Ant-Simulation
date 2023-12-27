package Ants;

import AntWorld.Anthill;

public class Worker extends BlueAnt implements Fighting, Collecting {

    public Worker(String name, int strength, int health, Anthill anthill) {
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
            returnToAnthill();
        } else {
            System.out.printf("%s, a %s ant found no larvae\n", name, color);
            currentVertex.semaphore.release();
        }

    }

    @Override
    public void attack() throws InterruptedException {
        currentVertex.semaphore.acquire();
        RedAnt enemy = currentVertex.lookForRedEnemy();
        if (enemy != null) {
            System.out.printf("%s, a %s ant is attacking %s, a %s ant\n", name, color, enemy.get_Name(), enemy.getColor());
            enemy.receiveDamage(strength);
            currentVertex.semaphore.release();
            returnToAnthill();
        } else {
            System.out.printf("%s a %s ant found no enemy\n", name, color);
            currentVertex.semaphore.release();
        }

    }

    @Override
    public void run() {
        while (alive) {
            try {
                randomMove();
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
            try {
                attack();
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
            try {
                collectLarvae();
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
