import AntWorld.Anthill;

public class LarvaeCollectChecker extends Thread
{
    World world;
    Anthill blue;
    Anthill red;

    public LarvaeCollectChecker(World world, Anthill blue, Anthill red)
    {
        this.world = world;
        this.blue = blue;
        this.red = red;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (blue.getAmount_of_food() > 0.5 * world.total_larvae)
            {
                System.out.println("BLUE WIN");
                break;
            }
            if (red.getAmount_of_food() > 0.5 * world.total_larvae)
            {
                System.out.println("RED WIN");
                break;
            }
        }
    }
}
