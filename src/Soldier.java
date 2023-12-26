public class Soldier extends RedAnt implements Fighting {
    public Soldier(String name, int strength, int health, Anthill anthill) {
        super(name, strength, health, anthill);
    }

    @Override
    public void attack(Ant enemy) {
        if (enemy instanceof BlueAnt) {
            System.out.println("Soldier is attacking");
            enemy.receiveDamage(strength);
            ReturnToAnthill();
        } else {
            System.out.println("Friendly fire disabled");
        }
    }

}
