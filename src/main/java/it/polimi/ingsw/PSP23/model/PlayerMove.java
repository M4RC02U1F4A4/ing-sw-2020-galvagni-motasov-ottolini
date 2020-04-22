package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.view.View;

public class PlayerMove {
    private final Player player;
    private final View view;
    private final int x;
    private final int y;
    private final Action a;


    public Player getPlayer() {
        return player;
    }

    public View getView() {
        return view;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Action getA() { return a; }


    public PlayerMove(Player player, View view, int x, int y, Action a) {
        this.player = player;
        this.view = view;
        this.x = x;
        this.y = y;
        this.a=a;
    }


}
