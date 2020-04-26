package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Atlas extends God {

    public Atlas() {
        super.setUpGod("Atlas");
    }

    @Override
    public void startTurn() {
        super.setUpTurn(1, 1);
    }

    @Override
    public void build(Cell c, Status b, Worker w) {
        this.remains_moves = 0;
        if (0 < remains_builds) {
            if (c.isNear(w)) {
                c.build(b);
                this.remains_builds--;
            }
            else {
                // TODO error, too far away
            }
        }
        else {
            //TODO error, already build
        }
    }

}

