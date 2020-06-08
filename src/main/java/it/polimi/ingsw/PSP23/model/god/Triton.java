package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Triton extends God {

    /**
     * Constructor
     */
    public Triton() {
        setUpGod("Triton");
    }

    /**
     * Each time your worker moves into a perimeter space, it may immediately move again
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     * @return 0 if the operation is successful,
     *           -1 if not near or occupied,
     *           -2 if already moved this turn,
     *           -3 athena block moved up moves,
     *           -4 (Artemis) not back to origin,
     *           -6 (Apollo) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        int i = super.move(c,w, map);
        if ((0 == c.getX() || 0 == c.getY() || 4 == c.getX() || 4 == c.getY()) && 0 == i) {
            this.remains_moves++;
        }
        return i;
    }
}
