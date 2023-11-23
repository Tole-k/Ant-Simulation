import java.util.ArrayList;

abstract public class Ant extends Thread
{
    String name;
    int strength;
    int health;
    String color;
    ArrayList<Integer> path;

    public void RandomMove()
    {
        System.out.println("RandomMove");
    }

    public void ReturnToAnthill()
    {
        System.out.println("ReturnToAnthill");
    }
}
