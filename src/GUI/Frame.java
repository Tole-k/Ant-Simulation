package GUI;

import Main.World;

import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame
{
    private final World world;
    private final RedPoints rp;
    private final BluePoints bp;
    private final AntGraph graph;

    public Frame()
    {
        super("Ant Simulation");

        setSize(1280, 720);
        getContentPane().setBackground(Color.BLACK);
        world = World.access();
        graph = new AntGraph();
        rp = new RedPoints(world.getRedAnthill());
        bp = new BluePoints(world.getBlueAnthill());
        JLabel sep = new JLabel(" : ");
        sep.setForeground(Color.WHITE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new FlowLayout());
        panel.add(bp);
        panel.add(sep);
        panel.add(rp);
        add(panel, BorderLayout.PAGE_START);
        add(graph, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void run()
    {
        new Thread(bp).start();
        new Thread(rp).start();
        new Thread(graph).start();
    }
}
