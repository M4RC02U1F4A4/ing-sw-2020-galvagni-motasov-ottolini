package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Minotaur extends God{

    /**
     * Constructor
     */
    public Minotaur() {
        super.setUpGod("Minotaur");
    }

    /**
     * your worker may move into an opponent worker's space, if their worker can be forced
     * one space straight backward to an unoccupied space at any level
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     * @return 0 if the operation is successful,
     *           -1 if not near or occupied,
     *           -2 if already moved this turn,
     *           -3 athena block moved up moves,
     *           -4 (Artemis) not back to origin,
     *           -5 (Minotaur) move out of map,
     *           -6 (Apollo/Minotaur) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        if (null == c.getWorker())
            return super.move(c, w, map);
        if (c.getWorker().getColor() == w.getColor())
            return -6;
        Cell b = w.getCell();
        int moreX = c.getX() - b.getX();
        int moreY = c.getY() - b.getY();
        Worker teseo = c.getWorker();
        Cell a = map.getCell(c.getX()+moreX, c.getY()+moreY);
        if (null == a)
            return -5;
        if (null == a.getWorker() && a.isNear(teseo, false)) {
            teseo.moveWorker(a);
            return super.move(c, w, map);
        }
        else return -1;
    }
}