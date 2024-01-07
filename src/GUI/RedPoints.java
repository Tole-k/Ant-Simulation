package GUI;

import AntWorld.Anthill;

import java.awt.*;

/**
 * The RedPoints class extends the Points class and represents the red points display in the GUI.
 * It includes methods for getting and setting the red anthill.
 */
public class RedPoints extends Points
{
    // The red anthill whose points are to be displayed
    private Anthill red;

    /**
     * Constructor for the RedPoints class.
     * It initializes the red points display with the given red anthill.
     * @param red The red anthill whose points are to be displayed.
     */
    public RedPoints(Anthill red)
    {
        super();
        this.setForeground(Color.RED);
        this.red = red;
    }

    /**
     * This method returns the red anthill.
     * @return The red anthill.
     */
    public Anthill getRed()
    {
        return red;
    }

    /**
     * This method sets the red anthill.
     * @param red The new red anthill.
     */
    public void setRed(Anthill red)
    {
        this.red = red;
    }
}