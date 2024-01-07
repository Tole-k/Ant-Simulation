package GUI;

import Main.World;

import javax.swing.*;

/**
 * The Points class is an abstract class that extends the JLabel class.
 * It represents a points display in the GUI and includes methods for updating the score and accessing the world.
 */
abstract public class Points extends JLabel
{
    // The world in which the points are displayed
    protected World world;

    /**
     * Constructor for the Points class.
     * It initializes the points display and accesses the world.
     */
    public Points()
    {
        super("0");
        world = World.access();
    }

    /**
     * This method updates the score.
     * It sets the text of the points display to the given points.
     * @param points The points to be displayed.
     */
    protected void updateScore(int points)
    {
        this.setText(String.format("%d", points));
    }

    /**
     * This method returns the world.
     * @return The world.
     */
    public World getWorld()
    {
        return world;
    }

    /**
     * This method sets the world.
     * @param world The new world.
     */
    public void setWorld(World world)
    {
        this.world = world;
    }
}