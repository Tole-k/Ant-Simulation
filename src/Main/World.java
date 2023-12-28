package Main;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Factories.VertexFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class World
{
    private static World self = null;
    private final double density;
    private final Anthill redAnthill;
    private final Anthill blueAnthill;
    private final ArrayList<String> places;
    private final VertexFactory vertexFactory;
    private final ArrayList<Vertex> world;
    protected int total_larvae;
    private int size;

    private World(int size, double density) throws FileNotFoundException
    {
        this.size = size;
        this.density = density;
        this.redAnthill = new Anthill("Red Anthill", 0.9, 0.1);
        this.blueAnthill = new Anthill("Blue Anthill", 0.1, 0.9);
        world = new ArrayList<>();
        world.add(redAnthill);
        world.add(blueAnthill);
        places = new ArrayList<>();
        Scanner s = new Scanner(new File("src/places.txt"));
        while (s.hasNextLine())
        {
            places.add(s.nextLine());
        }
        s.close();
        vertexFactory = VertexFactory.getInstance(places, blueAnthill, redAnthill);
        for (int i = world.size(); i < size; i++)
        {
            world.add(vertexFactory.initVertex());
        }
        total_larvae = VertexFactory.getTotal_larvae();
        for (int i = 0; i < world.size(); i++)
        {
            for (int j = i + 1; j < world.size(); j++)
            {
                if (Math.random() < density - world.get(i).getNeighbors().size() * 0.1)
                {
                    world.get(i).addNeighbors(world.get(j));
                    world.get(j).addNeighbors(world.get(i));
                }
            }
        }
    }

    public static World getInstance(int size, double density) throws FileNotFoundException
    {
        if (self == null)
        {
            self = new World(size, density);
        }
        return self;
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
        return places;
    }
}
