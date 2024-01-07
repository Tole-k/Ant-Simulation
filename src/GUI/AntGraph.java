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
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The AntGraph class extends the JPanel class and represents the graphical display of the ant world in the GUI.
 * It includes methods for painting the ant world and updating the graphical display.
 */
public class AntGraph extends JPanel
{
    // The world in which the ants live
    private World world;
    // The ant population whose movements are to be displayed
    private AntPopulation antPopulation;
    // The map of vertices to points for drawing the ant world
    private Map<Vertex, Pair> points;
    // The off-screen image for double buffering
    private Image offScreenImage;
    // The off-screen graphics for double buffering
    private Graphics offScreenGraphics;

    /**
     * Constructor for the AntGraph class.
     * It initializes the ant graph and accesses the world and ant population.
     */
    public AntGraph()
    {
        antPopulation = AntPopulation.access();
        world = World.access();
    }

    /**
     * This method is called when the AntGraph needs to be painted.
     * It paints the ant world and the ants in it.
     *
     * @param g The graphics context.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        points = new ConcurrentHashMap<>();
        Pair blue = new Pair(50, getHeight() - 100);
        Pair red = new Pair(getWidth() - 100, 50);
        for (Vertex v : world.getWorld())
        {
            int x;
            int y;
            if (v == world.getBlueAnthill())
            {
                x = blue.getX();
                y = blue.getY();
                g.setColor(Color.BLUE);
            } else if (v == world.getRedAnthill())
            {
                x = red.getX();
                y = red.getY();
                g.setColor(Color.RED);
            } else
            {
                if (v.getX() < 0)
                    v.calculate_x(this);
                if (v.getY() < 0)
                    v.calculate_y(this);
                x = min(max(v.getX(), 50), getWidth() - 50);
                y = min(max(v.getY(), 50), getHeight() - 50);
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
            Iterator<Map.Entry<Vertex, Pair>> it = points.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry<Vertex, Pair> entry = it.next();
                Vertex w = entry.getKey();
                Pair p = entry.getValue();
                int xw = p.getX();
                int yw = p.getY();
                if (v.getNeighbors().contains(w))
                {
                    g.drawLine(x + 20, y + 20, xw + 20, yw + 20);
                }
            }
            /*for (Map.Entry<Vertex, Pair> entry : points.entrySet())
            {
                Vertex w = entry.getKey();
                int xw = entry.getValue().getX();
                int yw = entry.getValue().getY();
                if (v.getNeighbors().contains(w))
                {
                    g.drawLine(x + 20, y + 20, xw + 20, yw + 20);
                }
            }*/
        }
    }

    /**
     * This method updates the graphical display of the ant world.
     * It paints the ant world and the ants in it off-screen and then copies the off-screen image to the screen.
     *
     * @param g The graphics context.
     */
    public void updateGraph(Graphics g)
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
            int x = pair.getX() + ant.getX();
            int y = pair.getY() + ant.getY();
            if (ant instanceof RedAnt)
                offScreenGraphics.setColor(Color.RED);
            else
                offScreenGraphics.setColor(Color.BLUE);
            offScreenGraphics.fillRect(x - 2, y - 2, 5, 5);
            offScreenGraphics.setColor(Color.BLACK);
            offScreenGraphics.drawRect(x - 3, y - 3, 6, 6);
        }
        for (Vertex v : World.access().getWorld())
        {
            offScreenGraphics.setColor(Color.ORANGE);
            if (v.getNumber_of_larvae() > 0)
                offScreenGraphics.drawString(String.valueOf(v.getNumber_of_larvae()), points.get(v).getX() + 40, points.get(v).getY() + 40);
        }

        g.drawImage(offScreenImage, 0, 0, this);
    }

    /**
     * This method returns the world.
     *
     * @return The world.
     */
    public World getWorld()
    {
        return world;
    }

    /**
     * This method sets the world.
     *
     * @param world The new world.
     */
    public void setWorld(World world)
    {
        this.world = world;
    }

    /**
     * This method returns the ant population.
     *
     * @return The ant population.
     */
    public AntPopulation getAntPopulation()
    {
        return antPopulation;
    }

    /**
     * This method sets the ant population.
     *
     * @param antPopulation The new ant population.
     */
    public void setAntPopulation(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
    }
}
