package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Chronus extends God {

    /**
     * Constructor
     */
    public Chronus() {
        this.setUpGod("Chronus");
    }

    /**
     * Win if there are at leas five complete tower on the board
     * @param w the worker moved is needed
     * @param completed_tower number of completed tower on the board
     * @return bool
     */
    @Override
    public boolean checkWin(Worker w, int completed_tower, boolean is_hera_in_game) {
        if ((null != w) && (super.checkWin(w, completed_tower, is_hera_in_game)))
            return true;
        else
            return (5 <= completed_tower);
    }

}
