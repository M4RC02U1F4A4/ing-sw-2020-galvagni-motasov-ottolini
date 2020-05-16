package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.view.View;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove>{

    private final Game game;
    private ArrayList<View> players=new ArrayList<>();


    public void addPlayer(Player p){
        Player lol=new Player(p.getName(), p.getIpAddress());
        game.addPlayer(lol);

    }
    public void addPlayerView(View v){
        players.add(v);
    }

    public void addGodToSpecificPlayer(Player p, String god){
        game.addGod(p,god);
    }

    public Controller(Game game) {
        super();
        this.game = game;

    }



    //TODO

    public synchronized void  performMove(PlayerMove move){
        //TODO: VERIFICARE CHE E' IL TURNO DEL GIOCATORE
        move.getView().showMessage("***REMOVED***");
        if(game.isPlayerTurn(move.getPlayer())){
            /*if(move.getA()== Action.MOVE){
                game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.MOVE,move.getnWorker());
            }
            else if(move.getA()== Action.BUILD){
                game.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.BUILD,move.getnWorker());
            }*/
            while(game.getCurrentPhase()!=Phase.END){
                playPhase(move);
            }

        }
        //TODO:UPDATE TURN
        game.nextTurn();

    }

    @Override
    public void update(PlayerMove message) {
        message.getView().showMessage("***REMOVED***");
        performMove(message);

    }

    public void playPhase(PlayerMove move){
        /*switch (game.getCurrentPhase()){
            case CHOOSE_WORKER:{

                break;
            }
        }*/
    }
}
