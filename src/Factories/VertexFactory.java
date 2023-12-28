package Factories;

import AntWorld.Anthill;
import AntWorld.Leaf;
import AntWorld.Stone;
import AntWorld.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class VertexFactory
{
    private static int total_larvae = 0;
    private static VertexFactory self = null;
    private final ArrayList<String> names;
    private final Random random;
    private String name = null;
    private int number_of_larvae;


    private VertexFactory(ArrayList<String> names)
    {
        this.names = names;
        this.random = new Random();
    }

    public static VertexFactory getInstance(ArrayList<String> names, Anthill blue, Anthill red)
    {
        if (self == null)
        {
            self = new VertexFactory(names);
        }
        return self;
    }

    public static int getTotal_larvae()
    {
        return total_larvae;
    }

    private void chooseRandomStats()
    {
        int nameInd = random.nextInt(names.size());
        name = names.get(nameInd);
        names.remove(nameInd);
        number_of_larvae = random.nextInt(6);
    }

    public Vertex initVertex()
    {
        chooseRandomStats();
        Vertex v;
        double type = Math.random();
        if (type < 0.2)
        {
            v = new Leaf(name, number_of_larvae);
        } else if (type < 0.5)
        {
            v = new Stone(name, number_of_larvae);
        } else
        {
            v = new Vertex(name, number_of_larvae);
        }
        total_larvae += number_of_larvae;
        return v;
    }

}
