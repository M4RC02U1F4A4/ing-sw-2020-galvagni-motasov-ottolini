package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.observer.Observable;

import java.util.ArrayList;

public class Game extends Observable<MoveOrBuildMessage> {
    private Map map=new Map();
    private ArrayList<Player> players;
    private ArrayList<String> takenGods=null;




    public void performeMove(int x, int y, Player player, Action action){
        if(action == Action.BUILD){
            //TODO build
        }
        else if(action == Action.MOVE){
            //TODO move
            //takenGods.add(players.get(0).getGod().choseRandomGod());
        }
        else return;//magari anche qui con un'eccezione
    }

}
