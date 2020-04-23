package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Atlas extends God {
    @Override
    public void startTurn(int x, int y) {
        super.startTurn(1, 1);
    }

    @Override
    public void build(Cell c, Status b, Worker w) {
        remains_moves = 0;
        if (0 < remains_builds) {
            if (c.isNear(c, w)) {
                c.build(b);
                remains_builds--;
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

