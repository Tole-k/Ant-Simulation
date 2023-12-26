import java.util.Stack;

abstract public class Ant extends Thread implements Returning, Moving, Dying, DroppingLarvae {
    String name;
    int strength;
    int health;
    String color;
    Stack<Vertex> path;
    Vertex currentVertex;
    Anthill anthill;

    public Ant(String name, int strength, int health, String color, Anthill anthill) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.color = color;
        path = new Stack<>();
        this.anthill = anthill;
        currentVertex = anthill;
    }

    @Override
    public void RandomMove() {
        Vertex next = currentVertex.neighbors.get((int) (Math.random() * currentVertex.neighbors.size()));
        path.push(next);
        Move(next);
    }

    @Override
    public void Move(Vertex v) {
        Vertex previous = currentVertex;
        currentVertex = v;
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

    @Override
    public void Die() {
        System.out.println("Ant dies");
    }

    @Override
    public void DropLarvae() {
        System.out.println("Larvae dropped");
    }
}
