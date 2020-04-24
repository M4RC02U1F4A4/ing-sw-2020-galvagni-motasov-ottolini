package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.view.View;

public class PlayerMove {
    private final Player player;
    private final View view;
    private final int x;
    private final int y;
    private final Action a;

    /**
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }
    //TODO: Javadoc
    public View getView() {
        return view;
    }
    //TODO: Javadoc
    public int getX() {
        return x;
    }
    //TODO: Javadoc
    public int getY() {
        return y;
    }
    //TODO: Javadoc
    public Action getA() { return a; }

    //TODO: Javadoc
    public PlayerMove(Player player, View view, int x, int y, Action a) {
        this.player = player;
        this.view = view;
        this.x = x;
        this.y = y;
        this.a=a;
    }


}
