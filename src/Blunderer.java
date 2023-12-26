public class Blunderer extends Collector {
    int dropChance;

    public Blunderer(String name, int strength, int health, Anthill anthill, int dropChance) {
        super(name, strength, health, anthill);
        this.dropChance = dropChance;
    }

    @Override
    public void ReturnToAnthill() {
        while (!path.empty()) {
            Vertex v = path.pop();
            if (v != currentVertex) {
                Move(v);
                if (Math.random() < dropChance) {
                    dropLarvae(1);
                }
            }
        }
    }
}
