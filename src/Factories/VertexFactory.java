package Factories;

import AntWorld.Leaf;
import AntWorld.Stone;
import AntWorld.Vertex;
import Main.Simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a factory for creating vertices with random attributes.
 * It uses the Singleton design pattern to ensure only one instance of the factory exists.
 * The factory can create vertices of different types (Leaf, Stone, Vertex) with different names and locations.
 */
public class VertexFactory
{
    // The total number of larvae in all vertices.
    private static int total_larvae = 0;
    // The single instance of the VertexFactory class.
    private static VertexFactory self = null;
    // Lists of names for the vertices.
    private final ArrayList<String> states;
    private final ArrayList<String> fantasy_places;
    // Random number generator for creating random attributes.
    private final Random random;
    // Attributes for the vertices.
    private String name = null;
    private int number_of_larvae;
    private double x;
    private double y;

    /**
     * The private constructor for the VertexFactory class.
     * It initializes the factory with lists of names.
     *
     * @param fantasy_places A list of fantasy states for the vertices.
     * @param states         A list of states names for the vertices.
     */
    private VertexFactory(ArrayList<String> fantasy_places, ArrayList<String> states)
    {
        this.fantasy_places = fantasy_places;
        this.states = states;
        this.random = new Random();
    }

    /**
     * This method returns the single instance of the VertexFactory class.
     * If the instance does not exist, it is created.
     *
     * @param fantasy_places A list of fantasy names for the vertices.
     * @param names          A list of names for the vertices.
     * @return The single instance of the VertexFactory class.
     */
    public static VertexFactory getInstance(ArrayList<String> fantasy_places, ArrayList<String> names)
    {
        if (self == null)
        {
            self = new VertexFactory(fantasy_places, names);
        }
        return self;
    }

    /**
     * This method returns the total number of larvae in all vertices.
     *
     * @return The total number of larvae.
     */
    public static int getTotal_larvae()
    {
        return total_larvae;
    }

    /**
     * This method chooses random attributes for the vertices.
     */
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

    /**
     * This method initializes a vertex with random attributes.
     * The type of vertex (Leaf, Stone, Vertex) is also chosen randomly.
     *
     * @return The initialized vertex.
     */
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