package it.polimi.ingsw.PSP23.view;

import it.polimi.ingsw.PSP23.model.Action;
import it.polimi.ingsw.PSP23.model.Message;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.model.PlayerMove;
import it.polimi.ingsw.PSP23.observer.Observable;
import it.polimi.ingsw.PSP23.observer.Observer;

public abstract class View extends Observable<PlayerMove>  implements Observer<Message>{
    private Player player;

    protected View(Player player){
        this.player=player;
    }

    protected Player getPlayer(){
        return player;
    }

    public abstract void showMessage(Object message);
    public abstract String getWhatClientSaid();

    void handleChoice(String command, String args){
        notify(new PlayerMove(player, this, command, args));
    }



}
