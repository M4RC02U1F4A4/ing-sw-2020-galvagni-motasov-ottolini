package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.view.View;

import java.util.ArrayList;

public class Controller implements Observer<PlayerMove>{
    private final Game game;
    private ArrayList<View> players = new ArrayList<>();
    private Action actionBeingPerformed;
    private Status whatToBuild = Status.FREE;
    private ArrayList<String> arguments = new ArrayList<>();
    private int x = 0, y = 0;
    private int chosenWorker = 0;


    public void addPlayer(Player p){
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
        move.getView().showMessage(move.getPlayer().getPlayerNumber()+"-"+(game.getCurrentPlayerNum()));
        if (game.isPlayerTurn(move.getPlayer())) {
            move.getView().showMessage("lode lode al san crispino");
            switch (move.getCommand()) {
                case "SELECT_GODS":
                    game.godChoose(arguments.get(0),arguments.get(1),arguments.get(2));
                    break;
                case "CHOOSE_GOD":
                    move.getView().showMessage(game.setGod(arguments.get(0)));
                    break;
                // come scritto nella documentazine whatToBuild e chosenWorker possono anche essere indefiniti con alcune operazioni.
                // TODO sostituire il placeholder chosenWorker with something working
                case "PLACE_WORKER":
                case "SELECT_WORKER":
                case "BUILD":
                case "MOVE":
                case "SKIP":
                    game.performeMove(actionBeingPerformed, whatToBuild, chosenWorker, x, y);
                    break;
            }
        }
        else {
            move.getView().showMessage("NON E' IL TUO TURNO!");
        }
    }

    @Override
    public void update(PlayerMove message) {
        setActionFromTheClient(message.getCommand(), message.getArgs(),message.getPlayer());
        performMove(message);

        //inizializzazione a fine turno
        arguments.clear();
        x=0;
        y=0;
        whatToBuild=Status.FREE;

    }

    // TODO forse questa funzione può decodificare direttamente il playermove e chiamare game.performemove?
    public void setActionFromTheClient(String msg, String args, Player him){
        String[] tmp=args.split(",");
        switch (msg){
            case "SELECT_GODS":{
                arguments.add(tmp[0]);  //Dio n.1
                arguments.add(tmp[1]);  //Dio n.2
                if(tmp.length==3){
                    arguments.add(tmp[2]);//Eventuale dio n.3
                }
                else{
                    arguments.add("ONLY2");
                }
                actionBeingPerformed=Action.SELECT_GODS;
                break;
            }
            case "CHOOSE_GOD":{
                actionBeingPerformed=Action.CHOOSE_GOD;
                arguments.add(tmp[0]);  //dio scelto per il giocatore
                break;
            }
            case "PLACE_WORKER":{
                actionBeingPerformed=Action.PLACE_WORKER;
                arguments.add(tmp[0]);//Numero worker
                arguments.add(tmp[1]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(1));
                arguments.add(tmp[2]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(2));
                break;
            }
            case "SELECT_WORKER":{
                actionBeingPerformed=Action.SELECT_WORKER;
                arguments.add(tmp[0]);//n. worker scelto
                break;
            }
            case "MOVE":{
                actionBeingPerformed=Action.MOVE;
                arguments.add(tmp[0]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(0));
                arguments.add(tmp[1]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(1));
                break;
            }
            case "BUILD":{
                actionBeingPerformed=Action.BUILD;
                arguments.add(tmp[0]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(0));
                arguments.add(tmp[1]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(1));
                if(tmp[2].equals("CUPOLA")){
                    whatToBuild=Status.CUPOLA;
                }
                else
                    whatToBuild=Status.BUILT;
                break;
            }

        }
    }
}
