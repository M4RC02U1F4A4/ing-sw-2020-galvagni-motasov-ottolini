package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.*;

public class Prometheus extends God {

    /**
     * Constructor
     */
    public Prometheus() {
        super.setUpGod("Prometheus");
    }

    /**
     * call the function <code>setUpTurn</code> with the parameters that indicate
     * the number of moves and builds that the player can do and
     * set other variables that are useful for check god's move
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
     * @return 0 if the operation is successful,
     *           -1 if not near or occupied,
     *           -2 if already moved this turn,
     *           -3 athena block moved up moves,
     *           -4 (Artemis) not back to origin,
     *           -6 (Apollo) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        int pos = w.getPosZ();
        int cont = super.move(c, w, map);
        if (0 == cont)
            if ((pos < c.height()) || (2 == this.remains_builds))
                remains_builds--;
        return cont;
    }

    /**
     * If your worker does not move up, it may build both before and after moving
     * @param c cell
     * @param b status of the cell
     * @param w worker that the player want to use to build
     * @return the level of the building
     */
    @Override
    public int build(Cell c, Status b, Worker w) {
        if (2 == this.remains_builds) {
            int cont = super.build(c, b, w);
            if (0 <= cont) {
                remains_moves = 1;
            }
            return cont;
        }
        else
            return super.build(c, b, w);
    }
}
