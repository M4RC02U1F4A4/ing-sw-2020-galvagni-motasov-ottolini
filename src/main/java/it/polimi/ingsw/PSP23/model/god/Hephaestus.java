package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Hephaestus extends God{
    private Cell previousBuild;

    /**
     * Constructor
     */
    public Hephaestus () {
        super.setUpGod("Hephaestus");
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
        this.previousBuild = null;
    }

    /**
     * Your worker may build one additiona block (not dome) on top of your first block
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
        if (2 == this.remains_builds) {
            int i = 0;
            i = super.build(c, b, w);
            if (0 <= i)
                this.previousBuild = c;
            return i;
        }
        if ((1 == this.remains_builds) && (c != this.previousBuild) || (3 == c.height())) {
            return -4;
        }
        return super.build(c, b, w);
    }
}
