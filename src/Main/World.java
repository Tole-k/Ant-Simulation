package Main;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Factories.VertexFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The World class represents the world in which the ants live.
 * It includes methods for initializing the world, adding vertices, and accessing world properties.
 */
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

    /**
     * Constructor for the World class.
     * It initializes the world based on the given size and density.
     *
     * @param size    The size of the world.
     * @param density The density of the world.
     */
    private World(int size, double density)
    {
        this.size = size;
        this.density = density;
        states = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/resources/states.txt");
        assert inputStream != null;
        Scanner s = new Scanner(inputStream);
        while (s.hasNextLine())
        {
            states.add(s.nextLine());
        }
        s.close();
        ArrayList<String> places = new ArrayList<>();
        inputStream = getClass().getResourceAsStream("/resources/places.txt");
        assert inputStream != null;
        s = new Scanner(inputStream);
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

    /**
     * This method initializes the world.
     * It is a static method that creates a new instance of the World class if it does not exist.
     *
     * @param size    The size of the world.
     * @param density The density of the world.
     */
    public static void init(int size, double density)
    {
        if (self == null)
        {
            self = new World(size, density);
        }
    }

    /**
     * This method provides access to the World instance.
     *
     * @return The World instance.
     */
    public static World access()
    {
        assert self != null;
        return self;
    }

    /**
     * This method adds a vertex to the world.
     */
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

    /**
     * This method returns the red anthill.
     *
     * @return The red anthill.
     */
    public Anthill getRedAnthill()
    {
        return redAnthill;
    }

    /**
     * This method returns the blue anthill.
     *
     * @return The blue anthill.
     */
    public Anthill getBlueAnthill()
    {
        return blueAnthill;
    }

    /**
     * This method returns the size of the world.
     *
     * @return The size of the world.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * This method returns the density of the world.
     *
     * @return The density of the world.
     */
    public double getDensity()
    {
        return density;
    }

    /**
     * This method returns the world.
     *
     * @return The world.
     */
    public ArrayList<Vertex> getWorld()
    {
        return world;
    }

    /**
     * This method returns the places in the world.
     *
     * @return The places in the world.
     */
    public ArrayList<String> getPlaces()
    {
        return states;
    }

    /**
     * This method returns the total number of larvae in the world.
     *
     * @return The total number of larvae.
     */
    public int getTotal_larvae()
    {
        return total_larvae;
    }

    /**
     * This method sets the total number of larvae in the world.
     *
     * @param total_larvae The total number of larvae.
     */
    public void setTotal_larvae(int total_larvae)
    {
        this.total_larvae = total_larvae;
    }
}
