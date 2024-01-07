package Factories;

import AntWorld.Anthill;
import Ants.*;
import Main.Simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a factory for creating ants with random attributes.
 * It uses the Singleton design pattern to ensure only one instance of the factory exists.
 * The factory can create both blue and red ants, with different types and attributes.
 */
public class AntFactory
{
    // The single instance of the AntFactory class.
    private static AntFactory self = null;
    // Lists of names for the ants.
    private final ArrayList<String> democratic_names;
    private final ArrayList<String> republican_names;
    // Anthills for the ants.
    private final Anthill red;
    private final Anthill blue;
    // Random number generator for creating random attributes.
    private final Random random;
    private final ArrayList<String> fantasy_names;
    // Attributes for the ants.
    private String name = null;
    private int strength;
    private int health;

    /**
     * The private constructor for the AntFactory class.
     * It initializes the factory with lists of names and anthills.
     *
     * @param fantasy_names A list of fantasy names for the ants.
     * @param democratic_names A list of democratic names for the ants.
     * @param republican_names A list of republican names for the ants.
     * @param blue The blue anthill.
     * @param red The red anthill.
     */
    private AntFactory(ArrayList<String> fantasy_names, ArrayList<String> democratic_names, ArrayList<String> republican_names, Anthill blue, Anthill red)
    {
        this.fantasy_names = fantasy_names;
        this.republican_names = republican_names;
        this.democratic_names = democratic_names;
        this.red = red;
        this.blue = blue;
        this.random = new Random();
    }

    /**
     * This method returns the single instance of the AntFactory class.
     * If the instance does not exist, it is created.
     *
     * @param fantasy_names A list of fantasy names for the ants.
     * @param democratic_names A list of democratic names for the ants.
     * @param republican_names A list of republican names for the ants.
     * @param blue The blue anthill.
     * @param red The red anthill.
     * @return The single instance of the AntFactory class.
     */
    public static AntFactory getInstance(ArrayList<String> fantasy_names, ArrayList<String> democratic_names, ArrayList<String> republican_names, Anthill blue, Anthill red)
    {
        if (self == null)
        {
            self = new AntFactory(fantasy_names, democratic_names, republican_names, blue, red);
        }
        return self;
    }

    /**
     * This method chooses random attributes for the ants.
     *
     * @param democratic A boolean indicating whether the ant is democratic.
     */
    private void chooseRandomStats(boolean democratic)
    {
        int nameInd;
        if (Simulation.FREEDOM_MODE)
        {
            if (democratic)
            {
                nameInd = random.nextInt(democratic_names.size());
                name = democratic_names.get(nameInd);
                democratic_names.remove(nameInd);
            } else
            {
                nameInd = random.nextInt(republican_names.size());
                name = republican_names.get(nameInd);
                republican_names.remove(nameInd);
            }
        } else
        {
            nameInd = random.nextInt(fantasy_names.size());
            name = fantasy_names.get(nameInd);
            fantasy_names.remove(nameInd);
        }
        strength = random.nextInt(10) + 10;
        health = random.nextInt(100) + 100;
    }

    /**
     * This method initializes a blue ant with random attributes.
     *
     * @return The initialized blue ant.
     */
    public BlueAnt initBlueAnt()
    {
        chooseRandomStats(true);
        BlueAnt ant;
        int type = random.nextInt(2);
        ant = switch (type)
        {
            case 0 -> new Drone(name, 0, health * 2, blue);
            case 1 -> new Worker(name, strength, health, blue);
            default -> null;
        };
        return ant;
    }

    /**
     * This method initializes a red ant with random attributes.
     *
     * @return The initialized red ant.
     */
    public RedAnt initRedAnt()
    {
        chooseRandomStats(false);
        RedAnt ant;
        int type = random.nextInt(3);
        ant = switch (type)
        {
            case 0 -> new Soldier(name, (int) (strength * 1.5), health, red);
            case 1 -> new Collector(name, strength / 2, health, red);
            case 2 -> new Blunderer(name, strength / 2, health, red);
            default -> null;
        };
        return ant;
    }

}