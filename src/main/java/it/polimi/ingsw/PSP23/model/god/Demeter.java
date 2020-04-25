package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Demeter extends God {
    private int prev_build_x;
    private int prev_build_y;

    public Demeter() {
        super.setUpGod("Demeter");
        this.prev_build_x = -1;
        this.prev_build_y = -1;
    }

    @Override
    public void startTurn() {
        super.setUpTurn(1, 2);
        this.prev_build_x = -1;
        this.prev_build_y = -1;
    }

    @Override
    public void build(Cell c, Status b, Worker w) {
        if (0 < this.remains_builds) {
            if ((c.getX() != this.prev_build_x)&&(c.getY() != this.prev_build_y)) {
                super.build(c, Status.BUILT, w);
                this.prev_build_x = c.getX();
                this.prev_build_y = c.getY();
            }
            else {
                // TODO error, already build here
            }
        }
        else {
            //TODO error, no more building allowed
        }
    }

}
