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

    //TODO: Javadoc
    public View getView() {
        return view;
    }

    public String getCommand() {
        return command;
    }

    public String getArgs() {
        return args;
    }

    //TODO: Javadoc
    public PlayerMove(Player player, View view, String command, String args) {
        this.player = player;
        this.view = view;
        this.command=command;
        this.args=args;
    }
}
