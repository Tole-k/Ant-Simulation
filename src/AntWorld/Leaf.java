package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

/**
 * This class represents a leaf in the ant world.
 * It extends the Vertex class and overrides the methods for looking for enemies.
 * The leaf always returns null when any ant looks for enemies.
 */
public class Leaf extends Vertex
{
    /**
     * The constructor for the Leaf class.
     * It initializes the leaf with a name, number of larvae, and location (x, y).
     *
     * @param name             The name of the leaf.
     * @param number_of_larvae The number of larvae at the leaf.
     * @param x                The x-coordinate of the leaf's location.
     * @param y                The y-coordinate of the leaf's location.
     */
    public Leaf(String name, int number_of_larvae, double x, double y)
    {
        super(name, number_of_larvae, x, y);
    }

    /**
     * This method looks for a red enemy at the leaf.
     * Since a leaf hides all ants, it returns null.
     *
     * @return null
     */
    @Override
    public RedAnt lookForRedEnemy()
    {
        return null;
    }

    /**
     * This method looks for a blue enemy at the leaf.
     * Since a leaf all ants, it returns null.
     *
     * @return null
     */
    @Override
    public BlueAnt lookForBlueEnemy()
    {
        return null;
    }
}