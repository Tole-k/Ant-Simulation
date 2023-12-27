public class DeathmatchChecker extends Thread
{
    AntPopulation antPopulation;

    public DeathmatchChecker(AntPopulation antPopulation)
    {
        this.antPopulation = antPopulation;
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
                break;
            }
            if (antPopulation.getBlue_ants().isEmpty())
            {
                System.out.println("RED WIN");
                break;
            }
            antPopulation.ant_semaphore.release();
        }
    }
}
