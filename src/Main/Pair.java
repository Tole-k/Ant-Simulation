package Main;

/**
 * The Pair class represents a pair of integers.
 * It includes methods for getting and setting the values of the pair.
 */
public class Pair
{
    private int x;
    private int y;

    /**
     * Constructor for the Pair class.
     * It initializes the pair with the given values.
     *
     * @param x The first integer in the pair.
     * @param y The second integer in the pair.
     */
    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the first integer in the pair.
     *
     * @return The first integer in the pair.
     */
    public int getX()
    {
        return x;
    }

    /**
     * This method sets the first integer in the pair.
     *
     * @param x The new value for the first integer in the pair.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * This method returns the second integer in the pair.
     *
     * @return The second integer in the pair.
     */
    public int getY()
    {
        return y;
    }

    /**
     * This method sets the second integer in the pair.
     *
     * @param y The new value for the second integer in the pair.
     */
    public void setY(int y)
    {
        this.y = y;
    }
}