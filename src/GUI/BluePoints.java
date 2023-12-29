package GUI;

import AntWorld.Anthill;

import java.awt.*;

public class BluePoints extends Points
{
    private Anthill blue;

    public BluePoints(Anthill blue)
    {
        super();
        this.setForeground(Color.BLUE);
        this.blue = blue;
    }

    public Anthill getBlue()
    {
        return blue;
    }

    public void setBlue(Anthill blue)
    {
        this.blue = blue;
    }
}