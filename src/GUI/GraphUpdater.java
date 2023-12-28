package GUI;

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
            antGraph.updateAnt(g);
            antGraph.updateLarvae(g);
            Thread.sleep(500);
        }
        return null;
    }
}
