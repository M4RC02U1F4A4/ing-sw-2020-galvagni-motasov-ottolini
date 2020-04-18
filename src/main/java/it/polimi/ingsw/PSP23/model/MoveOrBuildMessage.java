package it.polimi.ingsw.PSP23.model;

public class MoveOrBuildMessage {
    private final Player player;
    private final Map map;


    public MoveOrBuildMessage(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }
}
