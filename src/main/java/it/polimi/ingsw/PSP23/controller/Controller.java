package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.view.View;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove>{

    private final Game game;
    private ArrayList<View> players=new ArrayList<>();
    private Action actionBeingPerformed;
    private ArrayList<String> arguments=new ArrayList<>();


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
        if (game.isPlayerTurn(move.getPlayer())){
            while (game.getPhase()!=Phase.END){
                playPhase(move);
            }
        }


        //TODO:UPDATE TURN



    }

    @Override
    public void update(PlayerMove message) {
        performMove(message);

    }

    public void playPhase(PlayerMove move){
        switch (game.getPhase()){
            case GOD_CHOOSE:{

            }
        }
    }

    public void setActionFromTheClient(String msg, String args){
        //("SELECT_GODS")("CHOOSE_GOD")("PLACE_WORKER")("SELECT_WORKER")("MOVE")("BUILD"))
        String[] tmp=args.split(",");
        switch (msg){
            case "SELECT_GODS":{
                arguments.add(tmp[0]);  //Dio n.1
                arguments.add(tmp[1]);  //Dio n.2
                if(tmp.length==3){
                    arguments.add(tmp[3]);//Eventuale dio n.3
                }
                actionBeingPerformed=Action.SELECT_GODS;
                break;
            }
            case "COOSE_GOD":{
                actionBeingPerformed=Action.CHOOSE_GOD;
                arguments.add(tmp[0]);  //dio scelto per il giocatore
                break;
            }
            case "PLACE_WORKER":{
                actionBeingPerformed=Action.PLACE_WORKER;
                arguments.add(tmp[0]);//Numero worker
                arguments.add(tmp[1]);//Coordinata x
                arguments.add(tmp[2]);//Coordinata y
                break;
            }
            case "SELECT_WORKER":{
                actionBeingPerformed=Action.SELECT_WORKER;
                arguments.add(tmp[0]);//n. worker scelto
                break;
            }
            case "MOVE":{
                actionBeingPerformed=Action.MOVE;
                arguments.add(tmp[0]);//coordinata x del movimento
                arguments.add(tmp[1]);//coordinata y del movimento
                break;
            }
            case "BUILD":{
                actionBeingPerformed=Action.BUILD;
                arguments.add(tmp[0]);//coordinata x della costruzione
                arguments.add(tmp[1]);//coordinata y della costruzione
                arguments.add(tmp[2]);//Struttora normale o cupola
                break;
            }

        }
    }
}
