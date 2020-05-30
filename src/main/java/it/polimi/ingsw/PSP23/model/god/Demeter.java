package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Demeter extends God {
    private int prev_build_x;
    private int prev_build_y;

    /**
     * Constructor
     */
    public Demeter() {
        super.setUpGod("Demeter");
        this.prev_build_x = -1;
        this.prev_build_y = -1;
    }

    /**
     * TODO
     * @param moved_up indicate if athena moved up this turn
     */
    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 2, moved_up);
        this.prev_build_x = -1;
        this.prev_build_y = -1;
    }

    /**
     * You can build one additiona time, but not on the same space
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
        if ((c.getX() == this.prev_build_x)&&(c.getY() == this.prev_build_y))
            return -3;
        int i = 0;
        i = super.build(c, b, w);
        if (0 <= i) {
            this.prev_build_x = c.getX();
            this.prev_build_y = c.getY();
        }
        return i;
    }

}
