package it.polimi.ingsw.PSP23.model;

public class MoveMessage extends Message {
    public MoveMessage(Player player, Map map, int x, int y, int nWorker) {
        super(player, map, Action.MOVE, x, y, nWorker);
    }
}
