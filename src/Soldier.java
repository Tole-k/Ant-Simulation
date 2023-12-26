public class Soldier extends RedAnt implements Fighting {
    public Soldier(String name, int strength, int health, String color, Anthill anthill) {
        super(name, strength, health, color, anthill);
    }

    @Override
    public void attack(Ant enemy) {
        System.out.println("Soldier attack");
        ReturnToAnthill();
    }

}
