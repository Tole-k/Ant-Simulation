package Ants;

/**
 * Returning interface.
 * This interface represents the action of an ant returning to the anthill in the simulation.
 */
public interface Returning
{
    /**
     * Method for an ant to return to the anthill.
     * This method should be implemented by any ant class that needs to return to the anthill.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    void returnToAnthill() throws InterruptedException;
}