package it.polimi.ingsw.PSP23.model;

public class BuildMessage extends Message {
    public BuildMessage(Player player, Map map, int x, int y) {
        super(player, map, Action.BUILD, x, y);
    }
}