package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Athena extends God {
    private boolean moved_up;

    /**
     * Constructor
     */
    public Athena() {
        super.setUpGod("Athena");
    }

    /**
     * call the function <code>setUpTurn</code> with the parameters that indicate
     * the number of moves and builds that the player can do and
     * set other variables that are useful for check god's move
     * @param moved_up indicate if athena moved up this turn
     */
    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 1, false);
        this.moved_up = false;
    }

    /**
     * If yone of your worker moved up on your last turn, opponent workers cannot move up this turn
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     * @return 0 if the operation is successful,
     *        -1 if not near or occupied,
     *        -2 if already moved this turn,
     *        -3 athena block moved up moves,
     *        -4 (Artemis) not back to origin,
     *        -6 (Apollo) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        if (w.getPosZ() + 1 == c.height()) {
            int i = super.move(c, w, map);
            if (0 == i)
                this.moved_up = true;
            return i;
        }
        else
            return super.move(c, w, map);
    }

    /**
     * Check if moved up
     * @return bool
     */
    @Override
    public boolean AthenaMovedUp () {
        return moved_up;
    }

}
