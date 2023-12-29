package GUI;

import Main.Simulation;

public class InfoUpdater extends Updater
{
    private final InfoPanel infoPanel;

    public InfoUpdater(InfoPanel infoPanel)
    {
        this.infoPanel = infoPanel;

    }

    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            infoPanel.updateInfo();
            Thread.sleep(Simulation.SLEEP_TIME);
        }
        return null;
    }
}
