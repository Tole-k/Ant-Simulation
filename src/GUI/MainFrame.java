package GUI;

import Main.World;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame
{
    private final World world;
    private final RedPoints rp;
    private final BluePoints bp;
    private final AntGraph graph;
    private final InfoUpdater infoUpdater;
    private final ScoreUpdater scoreUpdater;
    private final GraphUpdater graphUpdater;

    public MainFrame()
    {
        super("Ant Simulation");

        setSize(1920, 1080);
        getContentPane().setBackground(Color.BLACK);
        world = World.access();
        graph = new AntGraph();
        rp = new RedPoints(world.getRedAnthill());
        bp = new BluePoints(world.getBlueAnthill());
        JLabel sep = new JLabel(" : ");
        sep.setForeground(Color.WHITE);
        setLayout(new BorderLayout());
        ScorePanel scorePanel = new ScorePanel(rp, bp);
        add(scorePanel, BorderLayout.PAGE_START);
        add(graph, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(new AddAntButton(true));
        buttonPanel.add(new AddAntButton(false));
        add(buttonPanel, BorderLayout.PAGE_END);
        InfoPanel infoPanel = new InfoPanel();
        add(infoPanel, BorderLayout.LINE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoUpdater = new InfoUpdater(infoPanel);
        scoreUpdater = new ScoreUpdater(scorePanel);
        graphUpdater = new GraphUpdater(graph);
        setVisible(true);
    }

    public void run() throws Exception
    {
        infoUpdater.execute();
        scoreUpdater.execute();
        graphUpdater.execute();
    }
}
