package Ants;

import AntWorld.Anthill;
import AntWorld.Vertex;

public class Blunderer extends Collector {
    private int dropChance;

    public Blunderer(String name, int strength, int health, Anthill anthill, int dropChance) {
        super(name, strength, health, anthill);
        this.dropChance = dropChance;
    }

    @Override
    public void returnToAnthill() {
        while (!path.empty()) {
            Vertex v = path.pop();
            if (v != currentVertex) {
                move(v);
                if (Math.random() < dropChance) {
                    dropLarvae(1);
                }
            }
        }
    }

    public int getDropChance() {
        return dropChance;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }
}
