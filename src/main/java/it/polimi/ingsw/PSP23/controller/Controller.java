package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove> {

    private final Game game;


    public void addPlayer(Player p){
        game.addPlayer(new Player(p.getName(), p.getIpAddress()));

    }

    public Controller(Game game) {
        super();
        this.game = game;

    }
    public void generateRandomGods(){
        game.chooseRandomGods();
    }


    //TODO
    private synchronized void  performMove(PlayerMove move){
        //TODO: VERIFICARE CHE E' IL TURNO DEL GIOCATORE



        if(move.getA()== Action.MOVE){
            game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.MOVE);
        }
        else if(move.getA()== Action.BUILD){
            game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.BUILD);
        }
        else return;//magari sollevando anche un'eccezione

    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }
}
