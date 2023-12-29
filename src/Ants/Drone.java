package Ants;

import AntWorld.Anthill;
import Main.Simulation;

public class Drone extends BlueAnt
{
    public Drone(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    @Override
    public void run()
    {
        while (alive)
        {
            try
            {
                randomMove();
            } catch (InterruptedException e)
            {
                break;
            }
            try
            {
                sleep(Simulation.SLEEP_TIME);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
