package it.polimi.ingsw.PSP23.view;

import it.polimi.ingsw.PSP23.model.Action;
import it.polimi.ingsw.PSP23.model.Message;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.model.PlayerMove;
import it.polimi.ingsw.PSP23.observer.Observable;
import it.polimi.ingsw.PSP23.observer.Observer;

public abstract class View extends Observable<PlayerMove>  implements Observer<Message>{
    private Player player;

    /**
     * Constructor
     * @param player the player who belongs this view
     */
    protected View(Player player){
        this.player=player;
    }

    /**
     * getter for Player
     * @return player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Method used to send a message to the player
     * @param message the message we want to send
     */
    public abstract void showMessage(Object message);

    /**
     * Method that delivers to the controller all what a client "says" (via keyboard if using cli or any interactions with th gui)
     * @param command the command that the controller needs to perform
     * @param args  the arguments related to the command
     */
    void handleChoice(String command, String args){
        notify(new PlayerMove(player, this, command, args));
    }

    /**
     * Closes the connection
     */
    public abstract void close();

    /**
     * Method used to find out if a match ended due to a timeout/connection error or because somebody won
     */
    public abstract void isOver();



}
