package Ants;

import AntWorld.Anthill;

/**
 * Drone class extends BlueAnt.
 * This class represents a drone ant in the simulation.
 */
public class Drone extends BlueAnt
{
    /**
     * Constructor for the Drone class.
     *
     * @param name     Name of the drone ant.
     * @param strength Strength of the drone ant.
     * @param health   Health of the drone ant.
     * @param anthill  Anthill the drone ant belongs to.
     */
    public Drone(String name, int strength, int health, Anthill anthill)
    {
        super(name, strength, health, anthill);
    }

    /**
     * Run method for the drone ant.
     * The drone ant moves randomly and sleeps in a loop while it is alive.
     */
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
                sleep(sleep_time);
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}