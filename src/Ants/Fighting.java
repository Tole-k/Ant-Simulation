package Ants;

/**
 * Fighting interface.
 * This interface represents the action of an ant attacking in the simulation.
 */
public interface Fighting
{
    /**
     * Method for an ant to attack.
     * This method should be implemented by any ant class that needs to attack.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    void attack() throws InterruptedException;
}