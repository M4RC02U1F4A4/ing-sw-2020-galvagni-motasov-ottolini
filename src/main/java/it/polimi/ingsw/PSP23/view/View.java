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

    void handleMove(int x, int y, int nWorker){
        System.out.println(player.getName());
        System.out.println(this);
        System.out.println(x);
        System.out.println(y);
        System.out.println(Action.MOVE);
        System.out.println(nWorker);
        notify(new PlayerMove(player, this , x, y, Action.MOVE, nWorker));
        showMessage(getWhatClientSaid());
    }

    void handleBuild(int x, int y, int nWorker){
        System.out.println(x+" "+y);
        notify(new PlayerMove(player, this , x, y, Action.BUILD, nWorker));
    }


}
