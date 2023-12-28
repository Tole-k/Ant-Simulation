package Main;

import GUI.MainFrame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulation
{

    public static int verbosity = 2;
    private final World world;
    private final AntPopulation antPopulation;
    private final MainFrame mainFrame;
    protected boolean ended = false;


    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices in the ant world: ");
        int size = 20;//s.nextInt();
        System.out.print("Enter the density of the ant world: ");
        double density = 0.40;// s.nextDouble();
        world = World.getInstance(size, density);
        System.out.print("Enter the number of red ants: ");
        int red_size = 10;// s.nextInt();
        System.out.print("Enter the number of blue ants: ");
        int blue_size = 10;// s.nextInt();
        antPopulation = AntPopulation.getInstance(red_size, blue_size);
        mainFrame = new MainFrame();
    }

    public void run() throws Exception
    {
        mainFrame.run();
        DeadRemoval deadRemoval = new DeadRemoval(antPopulation);
        deadRemoval.start();
        //DeathmatchChecker DMCheck = new DeathmatchChecker(antPopulation, this);
        /*LarvaeCollectChecker LCCheck = new LarvaeCollectChecker(this, (int) (world.getSize() * 1.5), world.getBlueAnthill(), world.getRedAnthill());
        LCCheck.start();
        DMCheck.start();
        while (true)
        {
            if (ended)
            {
                break;
            }
        }
        antPopulation.ant_semaphore.acquireUninterruptibly();
        for (Ant ant : antPopulation.getAnts())
        {
            ant.interrupt();
        }
        LCCheck.interrupt();
        deadRemoval.interrupt();
        DMCheck.interrupt();
        antPopulation.ant_semaphore.release();*/
    }
}
