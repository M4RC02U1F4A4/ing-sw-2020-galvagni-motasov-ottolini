package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Prometheus extends God {

    /**
     * Constructor
     */
    public Prometheus() {
        super.setUpGod("Prometheus");
    }

    /**
     * TODO
     * @param moved_up indicate if athena moved up this turn
     */
    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 2, moved_up);
    }

    /**
     * If your worker does not move up, it may build both before and after moving
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     *   @return 0 if the operation is successful,
     *           -1 if not near or occupied,
     *           -2 if already moved this turn,
     *           -3 athena block moved up moves,
     *           -4 (Artemis) not back to origin,
     *           -6 (Apollo) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        if ((w.getPosZ() < c.height()) || (2 == this.remains_builds)){
            this.remains_builds--;
        }
        this.remains_moves = 1;
        return super.move(c, w, map);
    }
}
