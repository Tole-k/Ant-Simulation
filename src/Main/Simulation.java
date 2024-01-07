package Main;

import GUI.MainFrame;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Simulation
{
    public static int VERBOSITY;
    public static boolean FREEDOM_MODE;
    public static int TICK_RATE = 10;
    public static int SLEEP_TIME = 1000 / TICK_RATE;
    private final AntPopulation antPopulation;
    private final MainFrame mainFrame;

    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enable the freedom mode? (y/N): ");
        String answer = s.nextLine();

        FREEDOM_MODE = answer.equals("y");
        do
        {
            System.out.print("Enter the verbosity level of logs (0-3): ");
            while (!s.hasNextInt())
            {
                System.out.print("Enter the verbosity level of logs (0-3): ");
                s.next();
            }
            VERBOSITY = s.nextInt();
        } while (VERBOSITY < 0 || VERBOSITY > 3);

        int size;
        do
        {
            System.out.print("Enter the number of vertices in the ant world (3-50): ");
            while (!s.hasNextInt())
            {
                System.out.print("Enter the number of vertices in the ant world (3-50): ");
                s.next();
            }
            size = s.nextInt();
        } while (size < 3 || size > 50);

        double density = 0.6;
        World.init(size, density);

        int red_size;
        do
        {
            System.out.print("Enter the initial number of red ants (0-29): ");
            while (!s.hasNextInt())
            {
                System.out.print("Enter the initial number of red ants (0-29): ");
                s.next();
            }
            red_size = s.nextInt();
        } while (red_size >= 30 || red_size < 0);

        int blue_size;
        do
        {
            System.out.print("Enter the initial number of blue ants (0-29): ");
            while (!s.hasNextInt())
            {
                System.out.print("Enter the initial number of blue ants (0-29): ");
                s.next();
            }
            blue_size = s.nextInt();
        } while (blue_size >= 30 || blue_size < 0);

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
