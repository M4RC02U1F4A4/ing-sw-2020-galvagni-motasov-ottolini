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

    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(1, 2, moved_up);
        this.previousBuild = null;
    }

    // permette di costruire due volte sulla stessa cella un blocco (non una cupola)
    @Override
    public int build(Cell c, Status b, Worker w) {
        if ((2 == this.remains_builds) || ((1 == this.remains_builds) && (c == this.previousBuild) && (3 > c.height()))) {
            this.previousBuild = c;
            return super.build(c, b, w);
        }
        else
            return -4;
    }
}
