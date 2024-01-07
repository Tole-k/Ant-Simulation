package Ants;

/**
 * Collecting interface.
 * This interface represents the action of an ant collecting larvae in the simulation.
 */
public interface Collecting
{
    /**
     * Method for an ant to collect larvae.
     * This method should be implemented by any ant class that needs to collect larvae.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    void collectLarvae() throws InterruptedException;
}