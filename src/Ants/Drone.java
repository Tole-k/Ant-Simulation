package Ants;

import AntWorld.Anthill;

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
                sleep(SLEEP_TIME);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
