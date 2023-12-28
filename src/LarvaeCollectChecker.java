import AntWorld.Anthill;

public class LarvaeCollectChecker extends Thread
{
    World world;
    Anthill blue;
    Anthill red;
    Simulation simulation;

    int LarvaeGoal;

    public LarvaeCollectChecker(World world, Simulation simulation, int LarvaeGoal, Anthill blue, Anthill red)
    {
        this.world = world;
        this.LarvaeGoal = LarvaeGoal;
        this.blue = blue;
        this.red = red;
        this.simulation = simulation;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (blue.getAmount_of_food() > LarvaeGoal)
            {
                System.out.println("BLUE WIN");
                break;
            }
            if (red.getAmount_of_food() > LarvaeGoal)
            {
                System.out.println("RED WIN");
                break;
            }
        }
        simulation.ended = true;
    }
}
