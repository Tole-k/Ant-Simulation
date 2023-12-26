public class Collector extends RedAnt implements Collecting {
    int collected_larvae;

    public Collector(String name, int strength, int health, String color, Anthill anthill) {
        super(name, strength, health, color, anthill);
    }

    @Override
    public void collectLarvae() {
        System.out.println("Larvae collected");
        if (collected_larvae >= strength)
            ReturnToAnthill();
    }
}
