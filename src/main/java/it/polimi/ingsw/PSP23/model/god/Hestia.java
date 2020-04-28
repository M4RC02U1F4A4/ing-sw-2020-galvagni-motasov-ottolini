package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

import javax.swing.*;

public class Hestia extends God {

    public Hestia() {
        setUpGod("Hestia");
    }

    @Override
    public void startTurn() {
        super.setUpTurn(1,2);
    }

    @Override
    public int build(Cell c, Status b, Worker w) {
        if (2 == this.remains_builds)
            return super.build(c, b, w);
        else {
            if (1 == this.remains_builds && (0 == c.getX() || 0 == c.getY() || 4 == c.getX() || 4 == c.getY()))
                return -5;
            else
                return super.build(c, b, w);
        }
    }
}
