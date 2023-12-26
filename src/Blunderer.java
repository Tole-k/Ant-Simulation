public class Blunderer extends Collector {
    public Blunderer(String name, int strength, int health, String color, Anthill anthill) {
        super(name, strength, health, color, anthill);
    }

    @Override
    public void ReturnToAnthill() {
        while (!path.empty()) {
            Vertex v = path.pop();
            if (v != currentVertex) {
                Move(v);
            }
        }
    }
}
