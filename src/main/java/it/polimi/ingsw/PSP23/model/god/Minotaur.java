package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Minotaur extends God{

    public Minotaur() {
        super.setUpGod("Minotaur");
    }

    @java.lang.Override
    public int move(Cell c, Worker w) {
        if (null == c.getWorker())
            return super.move(c, w);
        else {
            Cell b = w.getCell();
            int moreX = c.getX() - b.getX();
            int moreY = c.getY() - b.getY();
            Cell a = Map.getCell(c.getX()+moreX, c.getY()+moreY);
            if (null == a.getWorker()) {
                Worker teseo = c.getWorker();
                teseo.moveWorker(a);
                return super.move(c, w);
            }
            else {
                return -1; //TODO error, invalid move, cell not empty
            }
        }
    }
}
