// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Anthill a = new Anthill();
        Vertex v = new Vertex(2);
        a.addNeighbors(v);
        v.addNeighbors(a);
        Worker w = new Worker("work", 12, 12, a);
        w.RandomMove();
        w.collectLarvae();
        System.out.println(a.getAnts());
        System.out.println(v.getAnts());
        System.out.println(a.getAmount_of_food());
        System.out.println(v.getNumber_of_larvae());
    }
}