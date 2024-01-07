package AntWorld;

/**
 * This class represents a stone in the ant world.
 * It extends the Vertex class.
 */
public class Stone extends Vertex
{
    /**
     * The constructor for the Stone class.
     * It initializes the stone with a name, number of larvae, and location (x, y).
     *
     * @param name The name of the stone.
     * @param number_of_larvae The number of larvae at the stone.
     * @param x The x-coordinate of the stone's location.
     * @param y The y-coordinate of the stone's location.
     */
    public Stone(String name, int number_of_larvae, double x, double y)
    {
        super(name, number_of_larvae, x, y);
    }
}