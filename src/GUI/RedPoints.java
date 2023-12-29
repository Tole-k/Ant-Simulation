package GUI;

import AntWorld.Anthill;

import java.awt.*;

public class RedPoints extends Points
{
    private Anthill red;

    public RedPoints(Anthill red)
    {
        super();
        this.setForeground(Color.RED);
        this.red = red;
    }

    public Anthill getRed()
    {
        return red;
    }

    public void setRed(Anthill red)
    {
        this.red = red;
    }
}
