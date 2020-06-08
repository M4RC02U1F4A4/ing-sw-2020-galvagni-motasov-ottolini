package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Apollo extends God {

    /**
     * Constructor
     */
    public Apollo() {
        super.setUpGod("Apollo");
    }

    //TODO rework this
    /**
     * Your worker move into an opponent worker's space by forcing their worker
     * to the space yours just vacated
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     * @return 0 if the operation is successful,
     *        -1 if not near or occupied,
     *        -2 if already moved this turn,
     *        -3 athena block moved up moves,
     *        -4 (Artemis) not back to origin,
     *        -5 (Minotaur) move out of map,
     *        -6 (Apollo/Minotaur) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        if (!c.isOccupied())
            return super.move(c, w, map);
        else {
            Worker w1 = c.getWorker();
            Cell c1 = w.getCell();
            if (w.getColor() == w1.getColor())
                return -6;
            int r = super.move(c, w, map);
            if (0 == r)
                w1.moveWorker(c1);
            return r;
        }
    }

}
