package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Triton extends God {

    public Triton() {
        setUpGod("Triton");
    }

    @Override
    public int move(Cell c, Worker w, Map map) {
        int i = super.move(c,w, map);
        if (0 == c.getX() || 0 == c.getY() || 4 == c.getX() || 4 == c.getY()) {
            this.remains_moves++;
        }
        return i;
    }
}
