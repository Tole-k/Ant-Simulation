public class Soldier extends RedAnt implements Fighting {
    @Override
    public void attack() {
        System.out.println("Soldier attack");
        ReturnToAnthill();
    }

}
