package Ants;

import AntWorld.Vertex;

public interface Moving
{
    void move(Vertex v) throws InterruptedException;

    void randomMove() throws InterruptedException;

    void returnToAnthill() throws InterruptedException;
}
