package Main;

import Ants.Ant;
import Ants.BlueAnt;
import Ants.RedAnt;
import Factories.AntFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class AntPopulation
{
    private static AntPopulation self = null;
    private final ArrayList<Ant> ants;
    private final ArrayList<BlueAnt> blue_ants;
    private final ArrayList<RedAnt> red_ants;
    private final ArrayList<String> names;
    private final AntFactory antFactory;
    private final World world;
    public Semaphore ant_semaphore;
    private int red_size;
    private int blue_size;

    private AntPopulation(int red_size, int blue_size) throws FileNotFoundException
    {
        this.red_size = red_size;
        this.blue_size = blue_size;
        this.ant_semaphore = new Semaphore(1);
        ants = new ArrayList<>();
        red_ants = new ArrayList<>();
        blue_ants = new ArrayList<>();
        names = new ArrayList<>();
        Scanner s = new Scanner(new File("src/names.txt"));
        while (s.hasNextLine())
        {
            names.add(s.nextLine());
        }
        s.close();
        world = World.access();
        antFactory = AntFactory.getInstance(names, world.getBlueAnthill(), world.getRedAnthill());
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

    public static AntPopulation getInstance(int red_size, int blue_size) throws FileNotFoundException
    {
        if (self == null)
        {
            self = new AntPopulation(red_size, blue_size);
        }
        return self;
    }

    public static AntPopulation access()
    {
        return self;
    }

    public void AddBlueAnt()
    {
        BlueAnt ant = antFactory.initBlueAnt();
        ants.add(ant);
        blue_ants.add(ant);
        blue_size += 1;
        ant.start();
    }

    public void AddRedAnt()
    {
        RedAnt ant = antFactory.initRedAnt();
        ants.add(ant);
        red_ants.add(ant);
        red_size += 1;
        ant.start();
    }

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
        return names;
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
}
