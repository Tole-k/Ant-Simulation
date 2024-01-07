package Factories;

import AntWorld.Anthill;
import Ants.*;
import Main.Simulation;

import java.util.ArrayList;
import java.util.Random;

public class AntFactory
{
    private static AntFactory self = null;
    private final ArrayList<String> democratic_names;
    private final ArrayList<String> republican_names;
    private final Anthill red;
    private final Anthill blue;
    private final Random random;
    private final ArrayList<String> fantasy_names;
    private String name = null;
    private int strength;
    private int health;


    private AntFactory(ArrayList<String> fantasy_names, ArrayList<String> democratic_names, ArrayList<String> republican_names, Anthill blue, Anthill red)
    {
        this.fantasy_names = fantasy_names;
        this.republican_names = republican_names;
        this.democratic_names = democratic_names;
        this.red = red;
        this.blue = blue;
        this.random = new Random();
    }

    public static AntFactory getInstance(ArrayList<String> fantasy_names, ArrayList<String> democratic_names, ArrayList<String> republican_names, Anthill blue, Anthill red)
    {
        if (self == null)
        {
            self = new AntFactory(fantasy_names, democratic_names, republican_names, blue, red);
        }
        return self;
    }

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