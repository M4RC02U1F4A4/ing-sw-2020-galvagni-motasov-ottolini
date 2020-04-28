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

    protected abstract void showMessage(Object message);

    void handleMove(int x, int y){
        System.out.println(x+" "+y);
        notify(new PlayerMove(player, this , x, y, Action.MOVE));
    }

    void handleBuild(int x, int y){
        System.out.println(x+" "+y);
        notify(new PlayerMove(player, this , x, y, Action.BUILD));
    }


}
