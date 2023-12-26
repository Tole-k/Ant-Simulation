public class Worker extends BlueAnt implements Fighting, Collecting {
    public Worker(String name, int strength, int health, String color, Anthill anthill) {
        super(name, strength, health, color, anthill);
    }

    @Override
    public void collectLarvae() {
        System.out.println("Larvae collected");
        ReturnToAnthill();
    }

    @Override
    public void attack(Ant enemy) {
        System.out.println("Worker attack");
        ReturnToAnthill();
    }
}
