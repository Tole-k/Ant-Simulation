package Main;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Factories.VertexFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class World
{
    private static World self = null;
    private final double density;
    private final Anthill redAnthill;
    private final Anthill blueAnthill;
    private final ArrayList<String> states;
    private final VertexFactory vertexFactory;
    private final ArrayList<Vertex> world;
    private int total_larvae;
    private int size;

    private World(int size, double density) throws FileNotFoundException
    {
        this.size = size;
        this.density = density;
        states = new ArrayList<>();
        Scanner s = new Scanner(new File("src/resources/states.txt"));
        while (s.hasNextLine())
        {
            states.add(s.nextLine());
        }
        s.close();
        ArrayList<String> places = new ArrayList<>();
        s = new Scanner(new File("src/resources/places.txt"));
        while (s.hasNextLine())
        {
            places.add(s.nextLine());
        }
        s.close();
        String red_base, blue_base;
        if (Simulation.FREEDOM_MODE)
        {
            List<String> red_states = Arrays.asList("Wyoming", "West Virginia", "North Dakota", "Oklahoma", "Idaho", "Arkansas", "South Dakota", "Alabama", "Tennessee");
            List<String> blue_states = Arrays.asList("Vermont", "Massachusetts", "Maryland", "Hawaii", "California", "New York", "Washington", "Rhode Island", "Delaware");
            red_base = red_states.get((int) (Math.random() * red_states.size()));
            blue_base = blue_states.get((int) (Math.random() * blue_states.size()));
            states.remove(red_base);
            states.remove(blue_base);
        } else
        {
            red_base = "Red Anthill";
            blue_base = "Blue Anthill";
        }
        redAnthill = new Anthill(red_base, 0.9, 0.1);
        blueAnthill = new Anthill(blue_base, 0.1, 0.9);
        world = new ArrayList<>();
        world.add(redAnthill);
        world.add(blueAnthill);
        vertexFactory = VertexFactory.getInstance(places, states);
        for (int i = world.size(); i < size; i++)
        {
            world.add(vertexFactory.initVertex());
        }
        total_larvae = VertexFactory.getTotal_larvae();
        for (Vertex v1 : world)
        {
            java.util.Collections.shuffle(world);
            for (Vertex v2 : world)
            {
                if (v1 == v2 || v1.getNeighbors().contains(v2) || v2.getNeighbors().contains(v1) || (v1 instanceof Anthill && v2 instanceof Anthill))
                    continue;
                if (Math.random() < density - v2.getNeighbors().size() * 0.10 - v1.getNeighbors().size() * 0.10)
                {
                    v1.addNeighbors(v2);
                    v2.addNeighbors(v1);
                }
            }
        }
        if (redAnthill.getNeighbors().isEmpty())
        {
            Vertex v;
            do
            {
                v = world.get((int) (Math.random() * world.size()));
            } while (v instanceof Anthill);
            redAnthill.addNeighbors(v);
            v.addNeighbors(redAnthill);
        }
        if (blueAnthill.getNeighbors().isEmpty())
        {
            Vertex v;
            do
            {
                v = world.get((int) (Math.random() * world.size()));
            } while (v instanceof Anthill);
            blueAnthill.addNeighbors(v);
            v.addNeighbors(blueAnthill);
        }
    }

    public static void init(int size, double density) throws FileNotFoundException
    {
        if (self == null)
        {
            self = new World(size, density);
        }
    }

    public static World access()
    {
        assert self != null;
        return self;
    }

    public void addVertex()
    {
        Vertex v = vertexFactory.initVertex();
        for (Vertex vertex : world)
        {
            if (Math.random() < density)
            {
                v.addNeighbors(vertex);
                vertex.addNeighbors(v);
            }
        }
        world.add(v);
        total_larvae = VertexFactory.getTotal_larvae();
        size += 1;
    }

    public Anthill getRedAnthill()
    {
        return redAnthill;
    }

    public Anthill getBlueAnthill()
    {
        return blueAnthill;
    }

    public int getSize()
    {
        return size;
    }

    public double getDensity()
    {
        return density;
    }

    public ArrayList<Vertex> getWorld()
    {
        return world;
    }

    public ArrayList<String> getPlaces()
    {
        return states;
    }

    public int getTotal_larvae()
    {
        return total_larvae;
    }

    public void setTotal_larvae(int total_larvae)
    {
        this.total_larvae = total_larvae;
    }
}
