import Ants.Ant;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulation
{

    private final World world;
    private final AntPopulation antPopulation;

    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices in the ant world: ");
        int size = s.nextInt();
        System.out.print("Enter the density of the ant world: ");
        double density = s.nextDouble();
        world = World.getInstance(size, density);
        System.out.print("Enter the number of red ants: ");
        int red_size = s.nextInt();
        System.out.print("Enter the number of blue ants: ");
        int blue_size = s.nextInt();
        antPopulation = AntPopulation.getInstance(red_size, blue_size);
    }

    public void run() throws InterruptedException
    {
        for (Ant ant : antPopulation.getAnts())
        {
            ant.start();
        }
        DeathmatchChecker DMCheck = new DeathmatchChecker(antPopulation);
        DeadRemoval deadRemoval = new DeadRemoval(antPopulation);
        LarvaeCollectChecker LCCheck = new LarvaeCollectChecker(world, world.getBlueAnthill(), world.getRedAnthill());
        LCCheck.start();
        deadRemoval.start();
        DMCheck.start();
    }
}
