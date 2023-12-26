package Ants;

import AntWorld.Anthill;

public class Soldier extends RedAnt implements Fighting {
    public Soldier(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void attack(Ant enemy) {
        if (enemy instanceof BlueAnt) {
            System.out.println("Soldier is attacking");
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
    public void run()
    {
        while (alive) {
            randomMove();
            Ant enemy=lookForEnemy();
            if(enemy != null)
            {
                attack(enemy);
            }
        }
    }

}
