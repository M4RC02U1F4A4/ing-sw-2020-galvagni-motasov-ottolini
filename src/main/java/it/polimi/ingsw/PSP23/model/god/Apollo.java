package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Apollo extends God {

    @Override
    public void startTurn(int x, int y) {
        super.startTurn(1, 1);
    }

    @Override
    public int move(Cell c, Worker w) {
        if (c.isOccupied()) {
            Worker w1 = c.getWorker();
            Cell c1 = w.getCell();
            if (0 == super.move(c, w)) {
                w1.moveWorker(c1);
                return 0;
            }
            else {
                return -1;
            }
        }
        else {
            return super.move(c, w);
        }
    }

}
