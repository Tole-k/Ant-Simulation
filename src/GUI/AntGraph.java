package GUI;

import AntWorld.Anthill;
import AntWorld.Vertex;
import Main.World;

import java.awt.*;
import java.util.Map;

import static java.lang.Math.*;

public class AntGraph extends Canvas
{
    World world;
    Frame frame;
    private Map<Vertex, Pair> points;

    public AntGraph(Frame frame)
    {
        this.frame = frame;
        world = World.access();
    }

    public void paint(Graphics g)
    {
        setBackground(Color.BLACK);
        g.setColor(Color.BLUE);
        g.fillOval(50, getHeight() - 100, 50, 50);
        g.drawString(world.getBlueAnthill().getName(), 50, getHeight() - 100);
        g.setColor(Color.RED);
        g.fillOval(getWidth() - 100, 50, 50, 50);
        g.drawString(world.getRedAnthill().getName(), getWidth() - 100, 50);
        g.setColor(Color.WHITE);
        for (Vertex v : world.getWorld())
        {
            if (v instanceof Anthill)
                continue;
            int x = min(max((int) (random() * getWidth()), 50), getWidth() - 50);
            int y = min(max((int) (random() * getHeight()), 50), getHeight() - 50);
            while (x < 100 && y > getHeight() - 200 || y < 100 && x > getWidth() - 200)
            {
                y = min(max((int) (random() * getHeight()), 50), getHeight() - 50);
            }
            //points.put(v, new Pair(x, y));
            g.fillOval(x, y, 30, 30);
            g.drawString(v.getName(), x, y);
            /*for (Map.Entry<Vertex, Pair> entry : points.entrySet())
            {
                Vertex w = entry.getKey();
                int xw = entry.getValue().x;
                int yw = entry.getValue().y;
                if (v.getNeighbors().contains(w))
                {
                    g.drawLine(x, y, xw, yw);
                }
            }*/
        }
    }

    private static class Pair
    {
        public int x;
        public int y;

        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
