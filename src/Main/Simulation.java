package Main;

import GUI.MainFrame;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Simulation
{

    public static final int VERBOSITY = 3;
    public static int SLEEP_TIME;
    public static int TICK_RATE;
    private final AntPopulation antPopulation;
    private final MainFrame mainFrame;


    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of vertices in the ant world: ");
        int size = s.nextInt();
        //System.out.print("Enter the density of the ant world: ");
        double density = 0.4; // s.nextDouble();
        World.init(size, density);
        System.out.print("Enter the number of red ants: ");
        int red_size = s.nextInt();
        System.out.print("Enter the number of blue ants: ");
        int blue_size = s.nextInt();
        //System.out.print("Enter the simulation tick rate: ");
        TICK_RATE = 30; //s.nextInt();
        SLEEP_TIME = 1000 / TICK_RATE;
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
