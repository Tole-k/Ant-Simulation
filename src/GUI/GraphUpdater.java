package GUI;

import Main.Simulation;

import java.awt.*;

/**
 * The GraphUpdater class extends the Updater class and is responsible for updating the ant graph.
 * It continuously updates the ant graph at a rate determined by the sleep time of the simulation.
 */
public class GraphUpdater extends Updater
{
    // The ant graph to be updated
    private final AntGraph antGraph;

    /**
     * Constructor for the GraphUpdater class.
     * It initializes the ant graph.
     * @param antGraph The ant graph to be updated.
     */
    public GraphUpdater(AntGraph antGraph)
    {
        this.antGraph = antGraph;
    }

    /**
     * This method is called when the GraphUpdater is executed.
     * It continuously updates the ant graph at a rate determined by the sleep time of the simulation.
     * @return null.
     * @throws Exception if an error occurs during the update.
     */
    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            Graphics g = antGraph.getGraphics();
            antGraph.updateGraph(g);
            Thread.sleep(10);
        }
        return null;
    }
}