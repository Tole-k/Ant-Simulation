package GUI;

import Main.AntPopulation;

import javax.swing.*;
import java.awt.*;

public class AddAntButton extends JButton
{
    AntPopulation antPopulation;

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
}
