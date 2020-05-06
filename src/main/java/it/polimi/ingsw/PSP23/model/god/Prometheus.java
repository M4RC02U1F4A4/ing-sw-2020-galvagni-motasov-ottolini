package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Prometheus extends God {

    public Prometheus() {
        super.setUpGod("Prometheus");
    }

    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 2, moved_up);
    }

    //permette il movimento se ha costruito e non sale o se non si ha costruito nulla, in tal caso rimuove la possibilità di costruire una seconda volta
    @Override
    public int move(Cell c, Worker w, Map map) {
        if ((w.getPosZ() < c.height()) || (2 == this.remains_builds)){
            this.remains_builds--;
        }
        this.remains_moves = 1;
        return super.move(c, w, map);
    }
}
