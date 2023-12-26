package Ants;

import AntWorld.Anthill;

public class Collector extends RedAnt implements Collecting {
    protected int collected_larvae;

    public Collector(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
        collected_larvae = 0;
    }

    @Override
    public void collectLarvae() {
        if (currentVertex.getNumber_of_larvae() > 0) {
            currentVertex.setNumber_of_larvae(currentVertex.getNumber_of_larvae() - 1);
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
    public void dropLarvae(int amount) {
        collected_larvae -= amount;
        currentVertex.setNumber_of_larvae(currentVertex.getNumber_of_larvae() + amount);
    }

    @Override
    public void die() {
        dropLarvae(collected_larvae);
        super.die();
    }

    public int getCollected_larvae() {
        return collected_larvae;
    }

    public void setCollected_larvae(int collected_larvae) {
        this.collected_larvae = collected_larvae;
    }
}
