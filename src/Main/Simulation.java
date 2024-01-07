package Main;

import GUI.MainFrame;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * The Simulation class is responsible for setting up and running the simulation.
 * It initializes the world, the ant population, and the GUI.
 */
public class Simulation
{
    // Verbosity level of logs
    public static int VERBOSITY;
    // Freedom mode flag
    public static boolean FREEDOM_MODE;
    // Tick rate for the simulation
    public static int TICK_RATE = 10;
    // Sleep time based on the tick rate
    public static int SLEEP_TIME = 1000 / TICK_RATE;
    // Ant population instance
    private final AntPopulation antPopulation;
    // Main GUI frame
    private final MainFrame mainFrame;

    /**
     * Constructor for the Simulation class.
     * It initializes the world, the ant population, and the GUI based on user input.
     *
     * @throws FileNotFoundException if the required files are not found.
     */
    public Simulation() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enable the freedom mode? (y/N): ");
        String answer = s.nextLine();

        // Set freedom mode based on user input
        FREEDOM_MODE = answer.equals("y");
        do
        {
            System.out.print("Enter the verbosity level of logs (0-3): ");
            while (!s.hasNextInt())
            {
                System.out.print("Enter the verbosity level of logs (0-3): ");
                s.next();
            }
            // Set verbosity level based on user input
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
            // Set world size based on user input
            size = s.nextInt();
        } while (size < 3 || size > 50);

        double density = 0.6;
        // Initialize the world
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
            // Set initial number of red ants based on user input
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
            // Set initial number of blue ants based on user input
            blue_size = s.nextInt();
        } while (blue_size >= 30 || blue_size < 0);

        // Initialize the ant population
        antPopulation = AntPopulation.getInstance(red_size, blue_size);
        String title = FREEDOM_MODE ? "Welcome to the Land of the Free and the Home of the Brave!" : "Welcome to the Ant World!";
        // Initialize the main GUI frame
        mainFrame = new MainFrame(title);
    }

    /**
     * This method starts the simulation.
     * It runs the GUI, starts the ant population, and starts the dead removal process.
     *
     * @throws Exception if an error occurs during the simulation.
     */
    public void run() throws Exception
    {
        // Run the GUI
        mainFrame.run();
        sleep(1000);
        // Start the ant population
        antPopulation.start();
        // Start the dead removal process
        DeadRemoval deadRemoval = new DeadRemoval(antPopulation);
        deadRemoval.start();
    }
}