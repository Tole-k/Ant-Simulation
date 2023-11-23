public class Collector extends RedAnt{
    int collected_larvae;
    public void collectLarvae(){
        System.out.println("Larvae collected");
        if(collected_larvae >=strength)
            ReturnToAnthill();
    }
}
