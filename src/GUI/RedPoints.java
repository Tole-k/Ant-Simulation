package GUI;

import AntWorld.Anthill;

import java.awt.*;

public class RedPoints extends Points
{
    Anthill red;

    public RedPoints(Anthill red)
    {
        super();
        this.setForeground(Color.RED);
        this.red = red;
    }

}
