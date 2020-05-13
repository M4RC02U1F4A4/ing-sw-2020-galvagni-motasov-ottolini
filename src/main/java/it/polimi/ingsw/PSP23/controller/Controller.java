package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove> {

    private final Game game;


    public void addPlayer(Player p){
        game.addPlayer(new Player(p.getName(), p.getIpAddress()));

    }

    public void addGodToSpecificPlayer(Player p, String god){
        game.addGod(p,god);
    }

    public Controller(Game game) {
        super();
        this.game = game;

    }
    public void generateRandomGods(){
        game.chooseRandomGods();
    }
    public ArrayList<String> getChosenGods(){
        return game.getChosenGods();
    }


    //TODO

    public synchronized void  performMove(PlayerMove move){
        //TODO: VERIFICARE CHE E' IL TURNO DEL GIOCATORE
        if(game.isPlayerTurn(move.getPlayer())){
            if(move.getA()== Action.MOVE){
                game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.MOVE,move.getnWorker());
            }
            else if(move.getA()== Action.BUILD){
                game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.BUILD,move.getnWorker());
            }
        }
        //TODO:UPDATE TURN
        //game.setCurrentPhase(TurnManager.Phase.END);




    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }
}
