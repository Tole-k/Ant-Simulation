package Main;

/**
 * The DeadRemoval class extends the Thread class and is responsible for removing dead ants from the ant population.
 * It continuously checks the ant population and removes any ants that have been interrupted.
 */
public class DeadRemoval extends Thread
{
    // The ant population from which dead ants are to be removed
    private AntPopulation antPopulation;

    /**
     * Constructor for the DeadRemoval class.
     * It initializes the ant population.
     *
     * @param antPopulation The ant population from which dead ants are to be removed.
     */
    public DeadRemoval(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
    }

    /**
     * This method is called when the thread is started.
     * It continuously checks the ant population and removes any ants that have been interrupted.
     */
    @Override
    public void run()
    {
        while (true)
        {
            antPopulation.getAnt_semaphore().acquireUninterruptibly();
            for (int i = 0; i < antPopulation.getAnts().size(); i++)
            {
                if (antPopulation.getAnts().get(i).isInterrupted())
                {
                    antPopulation.removeAnt(antPopulation.getAnts().get(i));
                    i -= 1;
                }
            }
            antPopulation.getAnt_semaphore().release();
        }
    }

    /**
     * This method returns the ant population.
     *
     * @return The ant population.
     */
    public AntPopulation getAntPopulation()
    {
        return antPopulation;
    }

    /**
     * This method sets the ant population.
     *
     * @param antPopulation The new ant population.
     */
    public void setAntPopulation(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
    }
}