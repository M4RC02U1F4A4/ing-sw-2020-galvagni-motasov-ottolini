package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.view.View;

public class PlayerMove {
    private final Player player;
    private final View view;
    private final String command;
    private final String args;

    /**
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * @return command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return args
     */
    public String getArgs() {
        return args;
    }

    /**
     * Constructor
     * @param player player
     * @param view  view
     * @param command   command
     * @param args args
     */
    public PlayerMove(Player player, View view, String command, String args) {
        this.player = player;
        this.view = view;
        this.command=command;
        this.args=args;
    }
}
