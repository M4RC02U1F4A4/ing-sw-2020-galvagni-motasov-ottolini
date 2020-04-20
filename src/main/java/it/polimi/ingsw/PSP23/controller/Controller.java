package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.PlayerMove;
import it.polimi.ingsw.PSP23.observer.Observer;

public class Controller implements Observer<PlayerMove> {

    private final Map map;

    public Controller(Map map) {
        super();
        this.map = map;
    }
    //TODO
    private synchronized void  performMove(PlayerMove move){

    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }
}
