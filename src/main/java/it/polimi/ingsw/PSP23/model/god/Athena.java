package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Athena extends God {
    private boolean moved_up;

    public Athena() {
        super.setUpGod("Athena");
    }

    @Override
    public void startTurn() {
        super.setUpTurn(1, 1);
        this.moved_up = false;
    }

    @Override
    public int move(Cell c, Worker w) {
        if ((c.isNear(w)) && !c.isOccupied()) {
            this.moved_up = w.getPosZ() + 1 == c.height();
            if (0 < this.remains_moves) {
                if (-1 == this.starting_z)
                    this.starting_z = w.getPosZ();
                w.moveWorker(c);
                this.remains_moves--;
                return 0;
            }
            else {
                return -2;
            }
        }
        else {
            return -1;
        }
    }

    public boolean CheckMovedUp () {
        return moved_up;
    }

}
