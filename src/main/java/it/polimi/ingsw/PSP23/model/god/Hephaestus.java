package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;

public class Hephaestus extends God{
    private Cell previousBuild;

    public Hephaestus () {
        super.setUpGod("Hephaestus");
    }

    @java.lang.Override
    public void startTurn() {
        super.setUpTurn(1, 2);
    }

    // permette di costruire due volte sulla stessa cella un blocco (non una cupola)
    @java.lang.Override
    public void build(Cell c, Status b, Worker w) {
        if ((2 == this.remains_builds) || ((1 == this.remains_builds) && (c == this.previousBuild) && (3 > c.height())))
            super.build(c, b, w);
        else
            //todo error, invalid building slot;
        ;
    }
}
