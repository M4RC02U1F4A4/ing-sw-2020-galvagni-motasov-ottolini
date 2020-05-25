package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Chronus extends God {

    public Chronus() {
        this.setUpGod("Chronus");
    }

    @Override
    public boolean checkWin(Worker w, int completed_tower) {
        if ((null != w) && (super.checkWin(w, completed_tower)))
            return true;
        else
            return (5 <= completed_tower);
    }

}
