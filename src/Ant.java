import java.util.ArrayList;

abstract public class Ant extends Thread implements Returning {
    String name;
    int strength;
    int health;
    String color;
    ArrayList<Integer> path;

    public void RandomMove() {
        System.out.println("RandomMove");
    }

    @Override
    public void ReturnToAnthill() {
        System.out.println("ReturnToAnthill");
    }
}
