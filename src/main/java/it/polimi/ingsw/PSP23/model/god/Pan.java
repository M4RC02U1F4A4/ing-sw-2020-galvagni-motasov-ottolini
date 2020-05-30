package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Pan extends God {

    /**
     * Constructor
     */
    public Pan() {
        super.setUpGod("Pan");
    }

    /**
     * You also win if your worker moves down two or more levels
     * @param w the worker moved is needed
     * @param completed_tower number of completed tower on the board
     * @return bool true if going from>to 2>0, 3>0, 3>1, 2>3
     */
    @Override
    public boolean checkWin(Worker w, int completed_tower) {
        if (((2 <= this.starting_z) && (0 == w.getPosZ())) || ((3 == this.starting_z) && (1 == w.getPosZ())) || super.checkWin(w, completed_tower)) {
            if (this.is_hera_in_game) {
                return (0 != w.getPosX()) && (0 != w.getPosY()) && (4 != w.getPosX()) && (4 != w.getPosY());
            }
            else
                return true;
        }
        else
            return false;
    }
}
