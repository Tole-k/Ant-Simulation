package Ants;

/**
 * Dying interface.
 * This interface represents the actions of an ant receiving damage and dying in the simulation.
 */
public interface Dying
{
    /**
     * Method for an ant to receive damage.
     * This method should be implemented by any ant class that can receive damage.
     *
     * @param damage The amount of damage to be received.
     */
    void receiveDamage(int damage);

    /**
     * Method for an ant to die.
     * This method should be implemented by any ant class that can die.
     */
    void die();
}