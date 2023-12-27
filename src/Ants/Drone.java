package Ants;

import AntWorld.Anthill;

public class Drone extends BlueAnt {
    public Drone(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
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
        }
    }
}
