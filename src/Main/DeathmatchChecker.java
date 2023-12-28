package Main;

public class DeathmatchChecker extends Thread
{
    AntPopulation antPopulation;
    Simulation simulation;

    public DeathmatchChecker(AntPopulation antPopulation, Simulation simulation)
    {
        this.antPopulation = antPopulation;
        this.simulation = simulation;
    }

    @Override
    public void run()
    {
        while (true)
        {
            antPopulation.ant_semaphore.acquireUninterruptibly();
            if (antPopulation.getRed_ants().isEmpty())
            {
                System.out.println("BLUE WIN");
                antPopulation.ant_semaphore.release();
                break;
            }
            if (antPopulation.getBlue_ants().isEmpty())
            {
                System.out.println("RED WIN");
                antPopulation.ant_semaphore.release();
                break;
            }
            antPopulation.ant_semaphore.release();
        }
        simulation.ended = true;
    }
}
