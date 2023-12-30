package Ants;

public interface Dying
{
    void receiveDamage(int damage) throws InterruptedException;

    void die() throws InterruptedException;
}
