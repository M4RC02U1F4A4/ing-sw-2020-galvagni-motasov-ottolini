package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Prometheus extends God {

    public Prometheus() {
        super.setUpGod("Prometheus");
    }

    @java.lang.Override
    public void startTurn() {
        super.setUpTurn(1, 2);
        this.movedUp = false;
    }

    //permette il movimento se ha costruito e non sale o se non si ha costruito nulla, in tal caso rimuove la possibilità di costruire una seconda volta
    @java.lang.Override
    public int move(Cell c, Worker w) {
        if ((w.getPosZ() >= c.height()) && (1 == this.remains_builds))
            return super.move(c, w);
        else
            if (2 == remains_builds) {
                this.remains_builds = 1;
                return super.move(c, w);
            }
            else
                return -1; //todo error invalid move, can't move up after build
    }
}
