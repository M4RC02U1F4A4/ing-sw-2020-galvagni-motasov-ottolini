package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Athena extends God {
    private boolean moved_up;

    public Athena() {
        super.setUpGod("Athena");
        this.moved_up = false;
    }

    @Override
    public void startTurn() {
        super.setUpTurn(1, 2);
    }

    @Override
    public int move(Cell c, Worker w) {
        if ((c.isNear(w)) && !c.isOccupied()) {
            if (w.getPosZ() + 1 == c.height()) {
                this.moved_up = true;
            }
            else {
                this.moved_up = false;
            }
            if (0 < this.remains_moves) {
                if (-1 == this.starting_z)
                    this.starting_z = w.getPosZ();
                w.moveWorker(c);
                this.remains_moves--;
                return 0;
            }
            else {
                return -1;
                // error, no moves left
            }
        }
        else {
            return -1;
            // error, invalid move
        }
    }

    public boolean CheckMovedUp () {
        return moved_up;
    }

}
