package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Minotaur extends God{

    public Minotaur() {
        super.setUpGod("Minotaur");
    }

    @Override
    public int move(Cell c, Worker w, Map map) {
        if (null == c.getWorker())
            return super.move(c, w, map);
        Cell b = w.getCell();
        int moreX = c.getX() - b.getX();
        int moreY = c.getY() - b.getY();
        Cell a = map.getCell(c.getX()+moreX, c.getY()+moreY);
        if (null == a.getWorker()) {
            Worker teseo = c.getWorker();
            teseo.moveWorker(a);
            return super.move(c, w, map);
        }
        else {
            return -1;
        }
    }
}