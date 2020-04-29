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
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 1, false);
        this.moved_up = false;
    }

    @Override
    public int move(Cell c, Worker w) {
        if (w.getPosZ() + 1 == c.height()) {
            int i = super.move(c, w);
            if (0 == i)
                this.moved_up = true;
            return i;
        }
        else
            return super.move(c, w);
    }


    public boolean CheckMovedUp () {
        return moved_up;
    }

}
