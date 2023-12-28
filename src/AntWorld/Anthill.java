package AntWorld;

public class Anthill extends Vertex
{
    private int amount_of_food;

    public Anthill(String name, double x, double y)
    {
        super(name, 0, x, y);
        amount_of_food = 0;
    }

    public int getAmount_of_food()
    {
        return amount_of_food;
    }

    public void setAmount_of_food(int amount_of_food)
    {
        this.amount_of_food = amount_of_food;
    }

    public void addFood(int amount)
    {
        amount_of_food += amount;
    }

    public void removeFood(int amount)
    {
        amount_of_food -= amount;
    }
}
