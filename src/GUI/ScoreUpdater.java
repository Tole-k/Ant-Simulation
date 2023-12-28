package GUI;

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
            Thread.sleep(500);
        }
        return null;
    }
}
