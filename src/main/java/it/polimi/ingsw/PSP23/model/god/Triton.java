package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Worker;

public class Triton extends God {

    public Triton() {
        setUpGod("Triton");
    }

    @Override
    public int move(Cell c, Worker w) {
        if (1 == this.remains_builds && (0 == c.getX() || 0 == c.getY() || 4 == c.getX() || 4 == c.getY())) {
            this.remains_moves++;
            return super.move(c, w);
        }
        else
            return super.move(c, w);
    }
}
