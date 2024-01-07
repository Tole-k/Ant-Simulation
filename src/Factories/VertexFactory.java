package Factories;

import AntWorld.Leaf;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

import java.util.ArrayList;
import java.util.Random;

public class VertexFactory
{
    private static int total_larvae = 0;
    private static VertexFactory self = null;
    private final ArrayList<String> states;
    private final ArrayList<String> fantasy_places;
    private final Random random;
    private String name = null;
    private int number_of_larvae;
    private double x;
    private double y;


    private VertexFactory(ArrayList<String> fantasy_places, ArrayList<String> names)
    {
        this.fantasy_places = fantasy_places;
        this.states = names;
        this.random = new Random();
    }

    public static VertexFactory getInstance(ArrayList<String> fantasy_places, ArrayList<String> names)
    {
        if (self == null)
        {
            self = new VertexFactory(fantasy_places, names);
        }
        return self;
    }

    public static int getTotal_larvae()
    {
        return total_larvae;
    }

    private void chooseRandomStats()
    {
        int nameInd;
        if (Simulation.FREEDOM_MODE)
        {
            nameInd = random.nextInt(states.size());
            name = states.get(nameInd);
            states.remove(nameInd);
        } else
        {
            nameInd = random.nextInt(fantasy_places.size());
            name = fantasy_places.get(nameInd);
            fantasy_places.remove(nameInd);
        }
        number_of_larvae = random.nextInt(6);
        x = Math.random();
        y = Math.random();
    }

    public Vertex initVertex()
    {
        chooseRandomStats();
        Vertex v;
        double type = Math.random();
        if (type < 0.2)
        {
            v = new Leaf(name, number_of_larvae, x, y);
        } else if (type < 0.5)
        {
            v = new Stone(name, number_of_larvae, x, y);
        } else
        {
            v = new Vertex(name, number_of_larvae, x, y);
        }
        total_larvae += number_of_larvae;
        return v;
    }

}
