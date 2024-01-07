package Ants;

import AntWorld.Vertex;

/**
 * Moving interface.
 * This interface represents the actions of an ant moving in the simulation.
 */
public interface Moving
{
    /**
     * Method for an ant to move to a specific vertex.
     * This method should be implemented by any ant class that needs to move to a specific vertex.
     *
     * @param v The vertex to move to.
     * @throws InterruptedException if the thread is interrupted.
     */
    void move(Vertex v) throws InterruptedException;

    /**
     * Method for an ant to move randomly.
     * This method should be implemented by any ant class that needs to move randomly.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    void randomMove() throws InterruptedException;

    /**
     * Method for an ant to return to the anthill.
     * This method should be implemented by any ant class that needs to return to the anthill.
     *
     * @throws InterruptedException if the thread is interrupted.
     */
    void returnToAnthill() throws InterruptedException;
}