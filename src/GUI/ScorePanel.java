package GUI;

import Main.World;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel
{
    private final RedPoints redPoints;
    private final BluePoints bluePoints;

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

    public void updateScore()
    {
        redPoints.updateScore(World.access().getRedAnthill().getAmount_of_food());
        bluePoints.updateScore(World.access().getBlueAnthill().getAmount_of_food());
    }
}
