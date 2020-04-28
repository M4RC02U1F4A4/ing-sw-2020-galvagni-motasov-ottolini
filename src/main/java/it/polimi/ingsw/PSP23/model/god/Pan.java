package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Pan extends God {

    public Pan() {
        super.setUpGod("Pan");
    }

    // true if going from>to 2>0, 3>0, 3>1, 2>3
    @Override
    public boolean checkWin(Worker w) {
        return (((2 <= this.starting_z) && (0 == w.getPosZ())) || ((3 == this.starting_z) && (1 == w.getPosZ())) || super.checkWin(w));
    }
}
