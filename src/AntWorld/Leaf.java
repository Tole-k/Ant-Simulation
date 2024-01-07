package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

/**
 * This class represents a leaf in the ant world.
 * It extends the Vertex class and overrides the methods for looking for enemies.
 * In this case, the leaf does not have any enemies, so the methods return null.
 */
public class Leaf extends Vertex
{
    /**
     * The constructor for the Leaf class.
     * It initializes the leaf with a name, number of larvae, and location (x, y).
     *
     * @param name The name of the leaf.
     * @param number_of_larvae The number of larvae at the leaf.
     * @param x The x-coordinate of the leaf's location.
     * @param y The y-coordinate of the leaf's location.
     */
    public Leaf(String name, int number_of_larvae, double x, double y)
    {
        super(name, number_of_larvae, x, y);
    }

    /**
     * This method looks for a red enemy at the leaf.
     * Since a leaf does not have any enemies, it returns null.
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
     * Since a leaf does not have any enemies, it returns null.
     *
     * @return null
     */
    @Override
    public BlueAnt lookForBlueEnemy()
    {
        return null;
    }
}