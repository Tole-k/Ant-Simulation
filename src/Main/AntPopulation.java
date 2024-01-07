package Main;

import Ants.Ant;
import Ants.BlueAnt;
import Ants.RedAnt;
import Factories.AntFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * The AntPopulation class manages the population of ants in the simulation.
 * It includes methods for adding and removing ants, starting the simulation, and accessing population properties.
 */
public class AntPopulation
{
    // Singleton instance of the AntPopulation class
    private static AntPopulation self = null;
    // Lists of ants in the population
    private final ArrayList<Ant> ants;
    private final ArrayList<BlueAnt> blue_ants;
    private final ArrayList<RedAnt> red_ants;
    // List of democratic names for the ants
    private final ArrayList<String> democratic_names;
    // Factory for creating new ants
    private final AntFactory antFactory;
    // The world in which the ants live
    private final World world;
    // Semaphore for controlling access to the ant population
    private Semaphore ant_semaphore;
    // Sizes of the red and blue ant populations
    private int red_size;
    private int blue_size;

    /**
     * Constructor for the AntPopulation class.
     * It initializes the ant population based on the given sizes of the red and blue ant populations.
     *
     * @param red_size  The size of the red ant population.
     * @param blue_size The size of the blue ant population.
     */
    private AntPopulation(int red_size, int blue_size)
    {
        this.red_size = red_size;
        this.blue_size = blue_size;
        this.ant_semaphore = new Semaphore(1);
        ants = new ArrayList<>();
        red_ants = new ArrayList<>();
        blue_ants = new ArrayList<>();
        democratic_names = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream("/resources/Democrats.txt");
        assert inputStream != null;
        Scanner s = new Scanner(inputStream);
        while (s.hasNextLine())
        {
            democratic_names.add(s.nextLine());
        }
        s.close();
        ArrayList<String> republican_names = new ArrayList<>();
        inputStream = getClass().getResourceAsStream("/resources/Republicans.txt");
        assert inputStream != null;
        s = new Scanner(inputStream);
        while (s.hasNextLine())
        {
            republican_names.add(s.nextLine());
        }
        s.close();
        ArrayList<String> fantasy_names = new ArrayList<>();
        inputStream = getClass().getResourceAsStream("/resources/names.txt");
        assert inputStream != null;
        s = new Scanner(inputStream);
        while (s.hasNextLine())
        {
            fantasy_names.add(s.nextLine());
        }
        s.close();
        world = World.access();
        antFactory = AntFactory.getInstance(fantasy_names, democratic_names, republican_names, world.getBlueAnthill(), world.getRedAnthill());
        for (int i = 0; i < blue_size; i++)
        {
            BlueAnt ant = antFactory.initBlueAnt();
            ants.add(ant);
            blue_ants.add(ant);
        }
        for (int i = 0; i < red_size; i++)
        {
            RedAnt ant = antFactory.initRedAnt();
            ants.add(ant);
            red_ants.add(ant);
        }
    }

    /**
     * This method provides access to the AntPopulation instance.
     * It is a static method that creates a new instance of the AntPopulation class if it does not exist.
     *
     * @param red_size  The size of the red ant population.
     * @param blue_size The size of the blue ant population.
     * @return The AntPopulation instance.
     * @throws FileNotFoundException if the required files are not found.
     */
    public static AntPopulation getInstance(int red_size, int blue_size) throws FileNotFoundException
    {
        if (self == null)
        {
            self = new AntPopulation(red_size, blue_size);
        }
        return self;
    }

    /**
     * This method provides access to the AntPopulation instance.
     *
     * @return The AntPopulation instance.
     */
    public static AntPopulation access()
    {
        return self;
    }

    /**
     * This method adds a blue ant to the population.
     */
    public void AddBlueAnt()
    {
        BlueAnt ant = antFactory.initBlueAnt();
        ants.add(ant);
        blue_ants.add(ant);
        blue_size += 1;
        ant.start();
    }

    /**
     * This method adds a red ant to the population.
     */
    public void AddRedAnt()
    {
        RedAnt ant = antFactory.initRedAnt();
        ants.add(ant);
        red_ants.add(ant);
        red_size += 1;
        ant.start();
    }

    /**
     * This method removes an ant from the population.
     *
     * @param ant The ant to be removed.
     */
    public void removeAnt(Ant ant)
    {
        ants.remove(ant);
        if (ant instanceof RedAnt)
        {
            red_ants.remove(ant);
        } else if (ant instanceof BlueAnt)
        {
            blue_ants.remove(ant);
        }
    }

    /**
     * This method starts the simulation.
     * It starts all the ants in the population.
     *
     * @throws InterruptedException if an error occurs during the simulation.
     */
    public void start() throws InterruptedException
    {
        ant_semaphore.acquire();
        for (Ant ant : ants)
        {
            ant.start();
        }
        ant_semaphore.release();
    }

    public ArrayList<Ant> getAnts()
    {
        return ants;
    }

    public ArrayList<BlueAnt> getBlue_ants()
    {
        return blue_ants;
    }

    public ArrayList<RedAnt> getRed_ants()
    {
        return red_ants;
    }

    public int getRed_size()
    {
        return red_size;
    }

    public int getBlue_size()
    {
        return blue_size;
    }

    public ArrayList<String> getNames()
    {
        return democratic_names;
    }

    public AntFactory getAntFactory()
    {
        return antFactory;
    }

    public World getWorld()
    {
        return world;
    }

    public int getSize()
    {
        return red_size + blue_size;
    }

    public Semaphore getAnt_semaphore()
    {
        return ant_semaphore;
    }

    public void setAnt_semaphore(Semaphore ant_semaphore)
    {
        this.ant_semaphore = ant_semaphore;
    }
}
