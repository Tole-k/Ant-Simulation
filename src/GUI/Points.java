package GUI;

import Main.World;

import javax.swing.*;

abstract public class Points extends JLabel
{
    protected World world;

    public Points()
    {
        super("0");
        world = World.access();
    }

    protected void updateScore(int points)
    {
        this.setText(String.format("%d", points));
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
}
