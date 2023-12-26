public class Collector extends RedAnt implements Collecting {
    int collected_larvae;

    @Override
    public void collectLarvae() {
        System.out.println("Larvae collected");
        if (collected_larvae >= strength)
            ReturnToAnthill();
    }
}
