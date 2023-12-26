package Ants;

import AntWorld.Anthill;

public class Collector extends RedAnt implements Collecting {

    public Collector(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void collectLarvae() {
        if (currentVertex.getNumber_of_larvae() > 0) {
            currentVertex.removeLarvae(1);
            collected_larvae += 1;
            System.out.println("Larvae collected");
            if (collected_larvae >= strength) {
                returnToAnthill();
            }
        } else {
            System.out.println("No Larvae available");
        }
    }
    @Override
    public void run() {
        while (alive) {
            randomMove();
            collectLarvae();
        }
    }
}
