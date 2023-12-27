package Ants;

import AntWorld.Anthill;

public class Soldier extends RedAnt implements Fighting {
    public Soldier(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void attack() throws InterruptedException {
        currentVertex.semaphore.acquire();
        BlueAnt enemy = currentVertex.lookForBlueEnemy();
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
        }
    }

}
