package GUI;

import Main.Simulation;

import java.awt.*;

public class GraphUpdater extends Updater
{
    private final AntGraph antGraph;

    public GraphUpdater(AntGraph antGraph)
    {
        this.antGraph = antGraph;
    }

    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            Graphics g = antGraph.getGraphics();
            antGraph.updateGraph(g);
            Thread.sleep(Simulation.SLEEP_TIME);
        }
        return null;
    }
}
