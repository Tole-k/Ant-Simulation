package GUI;

import Main.AntPopulation;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a button for adding ants to the ant population.
 * It extends the JButton class and has a reference to the AntPopulation.
 * The color of the button indicates the type of ant to be added.
 */
public class AddAntButton extends JButton
{
    // The AntPopulation instance this button will interact with.
    private AntPopulation antPopulation;

    /**
     * The constructor for the AddAntButton class.
     * It initializes the button with the text "Add Ant" and sets the foreground color to black.
     * It also sets the background color based on the type of ant to be added (red or blue).
     * An action listener is added to the button to add the appropriate type of ant to the population when clicked.
     *
     * @param isRed A boolean indicating whether the button is for adding red ants (true) or blue ants (false).
     */
    public AddAntButton(boolean isRed)
    {
        super("Add Ant");
        setForeground(Color.BLACK);
        antPopulation = AntPopulation.access();
        if (isRed)
        {
            setBackground(Color.RED);
            addActionListener(e -> antPopulation.AddRedAnt());
        } else
        {
            setBackground(Color.BLUE);
            addActionListener(e -> antPopulation.AddBlueAnt());
        }
    }

    /**
     * This method returns the AntPopulation instance this button interacts with.
     *
     * @return The AntPopulation instance.
     */
    public AntPopulation getAntPopulation()
    {
        return antPopulation;
    }

    /**
     * This method sets the AntPopulation instance this button should interact with.
     *
     * @param antPopulation The AntPopulation instance to be set.
     */
    public void setAntPopulation(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
    }
}