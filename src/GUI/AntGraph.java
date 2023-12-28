package GUI;

import AntWorld.Leaf;
import AntWorld.Stone;
import AntWorld.Vertex;
import Ants.Ant;
import Ants.RedAnt;
import Main.AntPopulation;
import Main.Pair;
import Main.World;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AntGraph extends JPanel implements Runnable
{
    World world;
    AntPopulation antPopulation;
    MainFrame mainFrame;
    private Map<Vertex, Pair> points;
    private Pair blue;
    private Pair red;
    private Image offScreenImage;
    private Graphics offScreenGraphics;

    public AntGraph()
    {
        antPopulation = AntPopulation.access();
        world = World.access();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        points = new HashMap<>();
        blue = new Pair(50, getHeight() - 100);
        red = new Pair(getWidth() - 100, 50);
        for (Vertex v : world.getWorld())
        {
            int x;
            int y;
            if (v == world.getBlueAnthill())
            {
                x = blue.x;
                y = blue.y;
                g.setColor(Color.BLUE);
            } else if (v == world.getRedAnthill())
            {
                x = red.x;
                y = red.y;
                g.setColor(Color.RED);
            } else
            {
                if (v.getX() < 0)
                    v.calculate_x(this);
                if (v.getY() < 0)
                    v.calculate_y(this);
                x = min(max(v.getX(), 50), getWidth() - 50);
                y = min(max(v.getY(), 50), getHeight() - 50);
                /*while (x < 100 && y > getHeight() - 200 || y < 100 && x > getWidth() - 200)
                {
                    y = min(max((int) (random() * getHeight()), 50), getHeight() - 50);
                }*/
                if (v instanceof Stone)
                    g.setColor(Color.GRAY);
                else if (v instanceof Leaf)
                {
                    g.setColor(Color.GREEN);
                } else
                {
                    g.setColor(Color.WHITE);
                }
            }
            points.put(v, new Pair(x, y));
            g.fillOval(x, y, 40, 40);
            g.drawString(v.getName(), x, y);
            g.setColor(Color.WHITE);
            for (Map.Entry<Vertex, Pair> entry : points.entrySet())
            {
                Vertex w = entry.getKey();
                int xw = entry.getValue().x;
                int yw = entry.getValue().y;
                if (v.getNeighbors().contains(w))
                {
                    g.drawLine(x + 20, y + 20, xw + 20, yw + 20);
                }
            }
        }
    }

    @Override
    public void update(Graphics g)
    {
        if (offScreenImage == null)
        {
            offScreenImage = createImage(this.getWidth(), this.getHeight());
            offScreenGraphics = offScreenImage.getGraphics();
        }

        paintComponent(offScreenGraphics);

        for (Ant ant : antPopulation.getAnts())
        {
            Pair pair = points.get(ant.getCurrentVertex());
            int x = pair.x + ant.getX();
            int y = pair.y + ant.getY();
            if (ant instanceof RedAnt)
                offScreenGraphics.setColor(Color.RED);
            else
                offScreenGraphics.setColor(Color.BLUE);
            offScreenGraphics.fillRect(x - 2, y - 2, 5, 5);
            offScreenGraphics.setColor(Color.BLACK);
            offScreenGraphics.drawRect(x - 3, y - 3, 6, 6);
        }

        g.drawImage(offScreenImage, 0, 0, this);
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                SwingUtilities.invokeLater(() ->
                {
                    Graphics g = this.getGraphics();
                    update(g);
                });
                Thread.sleep(500);
            }
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
