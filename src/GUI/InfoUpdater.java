package GUI;

/**
 * The InfoUpdater class extends the Updater class and is responsible for updating the information panel.
 * It continuously updates the information panel.
 */
public class InfoUpdater extends Updater
{
    // The information panel to be updated
    private final InfoPanel infoPanel;

    /**
     * Constructor for the InfoUpdater class.
     * It initializes the information panel.
     *
     * @param infoPanel The information panel to be updated.
     */
    public InfoUpdater(InfoPanel infoPanel)
    {
        this.infoPanel = infoPanel;
    }

    /**
     * This method is called when the InfoUpdater is executed.
     * It continuously updates the information panel.
     *
     * @return null.
     * @throws Exception if an error occurs during the update.
     */
    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            infoPanel.updateInfo();
            Thread.sleep(10);
        }
        return null;
    }
}