package it.polimi.ingsw.PSP23.model;

import java.io.Serializable;

public class Message implements Serializable {
    private final Player player;
    private final Map map;
    private final Action action;
    private final int x;
    private final int y;
    private final int nWorker;

    /**
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @return action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * Used for send messages from controller to model
     * Constructor
     * @param player player
     * @param map map
     * @param action actin
     * @param x x position
     * @param y y position
     * @param nWorker   worker number
     */
    public Message(Player player, Map map, Action action, int x, int y, int nWorker) {
        this.player = player;
        this.map = map;
        this.action = action;
        this.x = x;
        this.y = y;
        this.nWorker = nWorker;
    }

}
