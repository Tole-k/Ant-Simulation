package Main;

public class DeadRemoval extends Thread
{
    AntPopulation antPopulation;

    public DeadRemoval(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
    }

    @Override
    public void run()
    {
        while (true)
        {
            antPopulation.ant_semaphore.acquireUninterruptibly();
            for (int i = 0; i < antPopulation.getAnts().size(); i++)
            {
                if (antPopulation.getAnts().get(i).isInterrupted())
                {
                    antPopulation.removeAnt(antPopulation.getAnts().get(i));
                    i -= 1;
                }
            }
            antPopulation.ant_semaphore.release();
        }
    }
}
