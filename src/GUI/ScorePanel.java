package GUI;

import Main.World;

import javax.swing.*;
import java.awt.*;

/**
 * The ScorePanel class extends the JPanel class and represents the score panel in the GUI.
 * It includes methods for updating the score.
 */
public class ScorePanel extends JPanel
{
    // The red points display
    private final RedPoints redPoints;
    // The blue points display
    private final BluePoints bluePoints;

    /**
     * Constructor for the ScorePanel class.
     * It initializes the score panel with the given red and blue points displays.
     * @param redPoints The red points display.
     * @param bluePoints The blue points display.
     */
    public ScorePanel(RedPoints redPoints, BluePoints bluePoints)
    {
        setBackground(Color.BLACK);
        this.redPoints = redPoints;
        this.bluePoints = bluePoints;
        setLayout(new FlowLayout());
        JLabel separator = new JLabel(" : ");
        separator.setForeground(Color.WHITE);
        this.add(bluePoints);
        this.add(separator);
        this.add(redPoints);
    }

    /**
     * This method updates the score.
     * It updates the red and blue points displays based on the amount of food in the red and blue anthills.
     */
    public void updateScore()
    {
        redPoints.updateScore(World.access().getRedAnthill().getAmount_of_food());
        bluePoints.updateScore(World.access().getBlueAnthill().getAmount_of_food());
    }
}