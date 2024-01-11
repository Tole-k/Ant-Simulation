package Main;

/**
 * The Main class is the entry point of the application.
 * It creates a new instance of the Simulation class and starts the simulation.
 */
public class Main
{
    /**
     * The main method is the entry point of the application.
     * It creates a new instance of the Simulation class and starts the simulation.
     *
     * @param args The command-line arguments.
     * @throws Exception if an error occurs during the simulation.
     */
    public static void main(String[] args) throws Exception
    {
        Simulation simulation = new Simulation();
        simulation.run();
    }
}