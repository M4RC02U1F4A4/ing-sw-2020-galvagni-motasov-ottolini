package it.polimi.ingsw.PSP23.model;

public class MoveOrBuildMessage {
    private final Player player;
    private final Map map;
    private final Action action;
    private final int x;
    private final int y;


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

    public MoveOrBuildMessage(Player player, Map map, Action action, int x, int y) {
        this.player = player;
        this.map = map;
        this.action = action;
        this.x = x;
        this.y = y;
    }
}
