package Ants;

import AntWorld.Anthill;

public class Worker extends BlueAnt implements Fighting, Collecting {

    public Worker(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void collectLarvae() {
        if (currentVertex.getNumber_of_larvae() > 0) {
            currentVertex.removeLarvae(1);
            collected_larvae += 1;
            System.out.println("Larvae collected");
            returnToAnthill();
        } else {
            System.out.println("No Larvae available");
        }
    }

    @Override
    public void attack(Ant enemy) {
        if (enemy instanceof RedAnt) {
            System.out.println("Worker is attacking");
            enemy.receiveDamage(strength);
            returnToAnthill();
        } else {
            System.out.println("Friendly fire disabled");
        }
    }
    @Override
    public Ant lookForEnemy()
    {
        return currentVertex.lookForEnemy();
    }

    @Override
    public void run() {
        while (alive) {
            randomMove();
            Ant enemy=lookForEnemy();
            if(enemy != null)
            {
                attack(enemy);
            }
            collectLarvae();
        }
    }
}
