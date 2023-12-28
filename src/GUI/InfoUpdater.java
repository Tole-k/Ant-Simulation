package GUI;

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
            Thread.sleep(500);
        }
        return null;
    }
}
