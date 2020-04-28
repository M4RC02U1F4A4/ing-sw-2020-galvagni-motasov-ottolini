package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Apollo extends God {

    public Apollo() {
        super.setUpGod("Apollo");
    }

    @Override
    public int move(Cell c, Worker w) {
        if (c.isOccupied()) {
            Worker w1 = c.getWorker();
            Cell c1 = w.getCell();
            int r = super.move(c, w);
            if (0 == r) {
                w1.moveWorker(c1);
                return 0;
            }
            else {
                return r;
            }
        }
        else {
            return super.move(c, w);
        }
    }

}
