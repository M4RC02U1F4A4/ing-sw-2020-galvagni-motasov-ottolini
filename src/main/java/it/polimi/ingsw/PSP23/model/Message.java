package it.polimi.ingsw.PSP23.model;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private final Player player;
    private final Map map;
    private final Action action;
    private final int x;
    private final int y;
    private final int nWorker;


    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public Action getAction() {
        return action;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Message(Player player, Map map, Action action, int x, int y, int nWorker) {
        this.player = player;
        this.map = map;
        this.action = action;
        this.x = x;
        this.y = y;
        this.nWorker = nWorker;
    }

}
