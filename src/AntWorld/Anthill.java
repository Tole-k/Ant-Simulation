package AntWorld;

/**
 * This class represents an anthill in the ant world.
 * It extends the Vertex class and has an additional attribute for the amount of food.
 */
public class Anthill extends Vertex
{
    // The amount of food in the anthill.
    private int amount_of_food;

    /**
     * The constructor for the Anthill class.
     * It initializes the anthill with a name and location (x, y).
     * The number of larvae is set to 0 and the amount of food is also set to 0.
     *
     * @param name The name of the anthill.
     * @param x The x-coordinate of the anthill's location.
     * @param y The y-coordinate of the anthill's location.
     */
    public Anthill(String name, double x, double y)
    {
        super(name, 0, x, y);
        amount_of_food = 0;
    }

    /**
     * This method returns the amount of food in the anthill.
     *
     * @return The amount of food.
     */
    public int getAmount_of_food()
    {
        return amount_of_food;
    }

    /**
     * This method sets the amount of food in the anthill.
     *
     * @param amount_of_food The amount of food to be set.
     */
    public void setAmount_of_food(int amount_of_food)
    {
        this.amount_of_food = amount_of_food;
    }

    /**
     * This method adds a specified amount of food to the anthill.
     *
     * @param amount The amount of food to be added.
     */
    public void addFood(int amount)
    {
        amount_of_food += amount;
    }

    /**
     * This method removes a specified amount of food from the anthill.
     *
     * @param amount The amount of food to be removed.
     */
    public void removeFood(int amount)
    {
        amount_of_food -= amount;
    }
}