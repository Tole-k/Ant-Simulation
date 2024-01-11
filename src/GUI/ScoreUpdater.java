package GUI;

import Main.Pair;
import Main.World;

import javax.swing.*;

/**
 * The ScoreUpdater class extends the Updater class and is responsible for updating the score panel.
 * It continuously updates the score panel.
 */
public class ScoreUpdater extends SwingWorker<Void, Pair>
{
    private final ScorePanel scorePanel;

    /**
     * Constructor for the ScoreUpdater class.
     * It initializes the score panel.
     *
     * @param scorePanel The score panel to be updated.
     */
    public ScoreUpdater(ScorePanel scorePanel)
    {
        this.scorePanel = scorePanel;
    }

    /**
     * This method is called when the ScoreUpdater is executed.
     * It continuously updates the score panel.
     *
     * @return null.
     * @throws Exception if an error occurs during the update.
     */
    @Override
    protected Void doInBackground() throws Exception
    {
        while (!isCancelled())
        {
            Pair p = new Pair(World.access().getRedAnthill().getAmount_of_food(), World.access().getBlueAnthill().getAmount_of_food());
            publish(p);
            Thread.sleep(10);
        }
        return null;
    }

    @Override
    protected void process(java.util.List<Pair> pairs)
    {
        Pair p = pairs.get(pairs.size() - 1);
        scorePanel.updateScore(p);
    }
}