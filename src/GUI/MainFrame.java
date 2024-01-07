package GUI;

import Main.World;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame class extends the JFrame class and represents the main frame of the GUI.
 * It includes methods for setting up the frame and running the simulation.
 */
public class MainFrame extends JFrame
{
    // The info updater for the info panel
    private final InfoUpdater infoUpdater;
    // The score updater for the score panel
    private final ScoreUpdater scoreUpdater;
    // The graph updater for the ant graph
    private final GraphUpdater graphUpdater;

    /**
     * Constructor for the MainFrame class.
     * It initializes the main frame with the given title and sets up the GUI components.
     * @param title The title of the main frame.
     */
    public MainFrame(String title)
    {
        super(title);

        setSize(1920, 1080);
        getContentPane().setBackground(Color.BLACK);
        World world = World.access();
        AntGraph graph = new AntGraph();
        RedPoints rp = new RedPoints(world.getRedAnthill());
        BluePoints bp = new BluePoints(world.getBlueAnthill());
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

    /**
     * This method starts the simulation.
     * It executes the info updater, score updater, and graph updater.
     */
    public void run()
    {
        infoUpdater.execute();
        scoreUpdater.execute();
        graphUpdater.execute();
    }
}