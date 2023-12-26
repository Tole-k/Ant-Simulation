public class Worker extends BlueAnt implements Fighting, Collecting {
    @Override
    public void collectLarvae() {
        System.out.println("Larvae collected");
        ReturnToAnthill();
    }

    @Override
    public void attack() {
        System.out.println("Worker attack");
        ReturnToAnthill();
    }
}
