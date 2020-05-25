package it.polimi.ingsw.PSP23.server;

import it.polimi.ingsw.PSP23.observer.Observer;

public interface ClientConnection {
    void closeConnection();
    void addObserver(Observer<String> observer);
    void asyncSend(Object message);
    void send(Object message);
    String getIpAddress();
}
