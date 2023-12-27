package AntWorld;

import Ants.BlueAnt;
import Ants.RedAnt;

public class Leaf extends Vertex {
    public Leaf(String name, int number_of_larvae) {
        super(name, number_of_larvae);
    }

    @Override
    public RedAnt lookForRedEnemy() {
        return null;
    }

    @Override
    public BlueAnt lookForBlueEnemy() {
        return null;
    }
}
