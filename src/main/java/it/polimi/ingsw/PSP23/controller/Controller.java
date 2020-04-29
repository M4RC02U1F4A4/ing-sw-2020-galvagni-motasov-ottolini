package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove> {

    private final Game game;
    private ArrayList<Player> players=new ArrayList<>();

    public void addPlayer(Player p){
        players.add(new Player(p.getName(), p.getIpAddress()));
    }

    public Controller(Game game) {
        super();
        this.game = game;
    }
    //TODO
    private synchronized void  performMove(PlayerMove move){
        if(move.getA()== Action.MOVE){
            //muovi
        }
        else if(move.getA()== Action.BUILD){
            //costruisci
        }
        else return;//magari sollevando anche un'eccezione

    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }
}
