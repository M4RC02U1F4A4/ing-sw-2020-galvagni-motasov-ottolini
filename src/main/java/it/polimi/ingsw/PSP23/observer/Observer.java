package it.polimi.ingsw.PSP23.observer;

public interface Observer<T> {
    void update(T message);
}
