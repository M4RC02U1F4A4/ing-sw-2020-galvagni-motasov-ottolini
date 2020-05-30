package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;


public class Hestia extends God {

    /**
     * Constructor
     */
    public Hestia() {
        setUpGod("Hestia");
    }

    /**
     * TODO
     * @param moved_up indicate if athena moved up this turn
     */
    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1,2, moved_up);
    }

    /**
     * Your worker may build one addition time, but this cannot be on a perimeter space
     * @param c cell
     * @param b status of the cell
     * @param w worker that the player want to use to build
     * @return  0, 1, 2, 3: the level built if there is no error
     *         -1 if cell is not near or is under the worker,
     *         -2 if the player already build in this turn,
     *         -3 (Demeter) if already build in this cell this turn,
     *         -4 (Hephaestus) if is a different building slot,
     *         -5 (Hesta) if perimetral slot build
     */
    @Override
    public int build(Cell c, Status b, Worker w) {
        if (2 == this.remains_builds)
            return super.build(c, b, w);
        else {
            if (1 == this.remains_builds && (0 == c.getX() || 0 == c.getY() || 4 == c.getX() || 4 == c.getY()))
                return -5;
            else
                return super.build(c, b, w);
        }
    }
}
