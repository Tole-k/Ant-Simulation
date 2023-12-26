import java.util.Stack;

abstract public class Ant extends Thread implements Returning, Moving, Dying {
    protected String name;
    protected int strength;
    protected int health;
    protected String color;
    protected Stack<Vertex> path;
    protected Vertex currentVertex;
    protected Anthill anthill;

    public Ant(String name, int strength, int health, String color, Anthill anthill) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.color = color;
        path = new Stack<>();
        this.anthill = anthill;
        currentVertex = anthill;
        currentVertex.addAnt(this);
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
        previous.removeAnt(this);
        currentVertex = v;
        currentVertex.addAnt(this);

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
    public void die() {
        System.out.println("Ant dies");
    }

    @Override
    public void receiveDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }

    public String get_Name() {
        return name;
    }

    public void set_Name(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Stack<Vertex> getPath() {
        return path;
    }

    public void setPath(Stack<Vertex> path) {
        this.path = path;
    }

    public Vertex getCurrentVertex() {
        return currentVertex;
    }

    public void setCurrentVertex(Vertex currentVertex) {
        this.currentVertex = currentVertex;
    }

    public Anthill getAnthill() {
        return anthill;
    }

    public void setAnthill(Anthill anthill) {
        this.anthill = anthill;
    }
}
