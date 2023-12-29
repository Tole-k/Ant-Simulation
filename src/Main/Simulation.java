package Main;

import GUI.MainFrame;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Simulation
{

    public static int verbosity = 2;
    private final AntPopulation antPopulation;
    private final MainFrame mainFrame;


    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices in the ant world: ");
        int size = s.nextInt();
        System.out.print("Enter the density of the ant world: ");
        double density = s.nextDouble();
        World.getInstance(size, density);
        System.out.print("Enter the number of red ants: ");
        int red_size = s.nextInt();
        System.out.print("Enter the number of blue ants: ");
        int blue_size = s.nextInt();
        antPopulation = AntPopulation.getInstance(red_size, blue_size);
        mainFrame = new MainFrame();
    }

    public void run() throws Exception
    {
        mainFrame.run();
        sleep(1000);
        antPopulation.start();
        DeadRemoval deadRemoval = new DeadRemoval(antPopulation);
        deadRemoval.start();
    }
}
