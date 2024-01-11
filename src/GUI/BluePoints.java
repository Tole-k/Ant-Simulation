package GUI;

import AntWorld.Anthill;

import java.awt.*;

/**
 * The BluePoints class extends the Points class and represents the blue points display in the GUI.
 * It includes methods for getting and setting the blue anthill.
 */
public class BluePoints extends Points
{
    // The blue anthill whose points are to be displayed
    private Anthill blue;

    /**
     * Constructor for the BluePoints class.
     * It initializes the blue points display with the given blue anthill.
     *
     * @param blue The blue anthill whose points are to be displayed.
     */
    public BluePoints(Anthill blue)
    {
        super();
        this.setForeground(Color.BLUE);
        this.blue = blue;
    }

    /**
     * This method returns the blue anthill.
     *
     * @return The blue anthill.
     */
    public Anthill getBlue()
    {
        return blue;
    }

    /**
     * This method sets the blue anthill.
     *
     * @param blue The new blue anthill.
     */
    public void setBlue(Anthill blue)
    {
        this.blue = blue;
    }
}