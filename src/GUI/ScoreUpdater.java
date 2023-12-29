package GUI;

import Main.Simulation;

public class ScoreUpdater extends Updater
{
    private final ScorePanel scorePanel;

    public ScoreUpdater(ScorePanel scorePanel)
    {
        this.scorePanel = scorePanel;
    }

    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            scorePanel.updateScore();
            Thread.sleep(Simulation.SLEEP_TIME);
        }
        return null;
    }
}
