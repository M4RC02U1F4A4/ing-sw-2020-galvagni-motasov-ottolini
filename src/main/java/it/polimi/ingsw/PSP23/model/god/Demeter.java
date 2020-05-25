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
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 2, moved_up);
        this.prev_build_x = -1;
        this.prev_build_y = -1;
    }

    @Override
    public int build(Cell c, Status b, Worker w) {
        if ((c.getX() == this.prev_build_x)&&(c.getY() == this.prev_build_y))
            return -3;
        int i = 0;
        i = super.build(c, b, w);
        if (0 <= i) {
            this.prev_build_x = c.getX();
            this.prev_build_y = c.getY();
        }
        return i;
    }

}
