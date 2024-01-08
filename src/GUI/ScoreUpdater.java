package GUI;

import Main.Simulation;

/**
 * The ScoreUpdater class extends the Updater class and is responsible for updating the score panel.
 * It continuously updates the score panel at a rate determined by the sleep time of the simulation.
 */
public class ScoreUpdater extends Updater
{
    // The score panel to be updated
    private final ScorePanel scorePanel;

    /**
     * Constructor for the ScoreUpdater class.
     * It initializes the score panel.
     * @param scorePanel The score panel to be updated.
     */
    public ScoreUpdater(ScorePanel scorePanel)
    {
        this.scorePanel = scorePanel;
    }

    /**
     * This method is called when the ScoreUpdater is executed.
     * It continuously updates the score panel at a rate determined by the sleep time of the simulation.
     * @return null.
     * @throws Exception if an error occurs during the update.
     */
    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            scorePanel.updateScore();
            Thread.sleep(10);
        }
        return null;
    }
}