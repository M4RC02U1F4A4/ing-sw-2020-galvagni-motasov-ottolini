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
        game.addPlayer(p.getName(),p.getIpAddress());

    }
    public void addPlayerView(View v){
        players.add(v);
    }


    public Controller(Game game) {
        super();
        this.game = game;

    }



    //TODO

    public synchronized void  performMove(PlayerMove move){
        //TODO: VERIFICARE CHE E' IL TURNO DEL GIOCATORE



        //TODO:UPDATE TURN



    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }

    /*public void playPhase(PlayerMove move){
        switch (game.getCurrentPhase()){
            case CHOOSE_WORKER:{

                break;
            }
        }
    }*/
}
