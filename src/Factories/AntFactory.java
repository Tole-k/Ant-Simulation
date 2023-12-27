package Factories;

import AntWorld.Anthill;
import Ants.*;

import java.util.ArrayList;
import java.util.Random;

public class AntFactory
{
    private static AntFactory self = null;
    private final ArrayList<String> names;
    private final Anthill red;
    private final Anthill blue;
    private final Random random;
    private String name = null;
    private int strength;
    private int health;


    private AntFactory(ArrayList<String> names, Anthill blue, Anthill red)
    {
        this.names = names;
        this.red = red;
        this.blue = blue;
        this.random = new Random();
    }

    public static AntFactory getInstance(ArrayList<String> names, Anthill blue, Anthill red)
    {
        if (self == null)
        {
            self = new AntFactory(names, blue, red);
        }
        return self;
    }

    private void chooseRandomStats()
    {
        int nameInd = random.nextInt(names.size());
        name = names.get(nameInd);
        names.remove(nameInd);
        strength = random.nextInt(10) + 10;
        health = random.nextInt(100) + 100;
    }

    public BlueAnt initBlueAnt()
    {
        chooseRandomStats();
        BlueAnt ant;
        int type = random.nextInt(2);
        ant = switch (type)
        {
            case 0 -> new Drone(name, strength, health, blue);
            case 1 -> new Worker(name, strength, health, blue);
            default -> null;
        };
        return ant;
    }

    public RedAnt initRedAnt()
    {
        chooseRandomStats();
        RedAnt ant;
        int type = random.nextInt(3);
        ant = switch (type)
        {
            case 0 -> new Soldier(name, strength, health, red);
            case 1 -> new Collector(name, strength, health, red);
            case 2 -> new Blunderer(name, strength, health, red);
            default -> null;
        };
        return ant;
    }

}