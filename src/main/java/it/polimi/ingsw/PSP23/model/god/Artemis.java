package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Artemis extends God {
    private int starting_x;
    private int starting_y;

    /**
     * Constructor
     */
    public Artemis() {
        super.setUpGod("Artemis");
        this.starting_x = -1;
        this.starting_y = -1;
    }

    /**
     * TODO
     * @param moved_up indicate if athena moved up this turn
     */
    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(2, 1, moved_up);
        this.starting_x = -1;
        this.starting_y = -1;
    }

    /**
     * Your worker can may move one additiona time, but not back to its initial space
     * @param c cell in which the player want to move the worker
     * @param w worker that the player want to move
     * @param map used only for minotaur power
     * @return 0 if the operation is successful,
     *        -1 if not near or occupied,
     *        -2 if already moved this turn,
     *        -3 athena block moved up moves,
     *        -4 (Artemis) not back to origin,
     *        -6 (Apollo) tried to move in friendly occupied cell.
     */
    @Override
    public int move(Cell c, Worker w, Map map) {
        if ((-1 == this.starting_y)&&(-1 == this.starting_x)) {
            this.starting_x = w.getPosX();
            this.starting_y = w.getPosY();
            return super.move(c, w, map);
        }
        else {
            if ((c.getY() == this.starting_y)&&(c.getX() == this.starting_x)) {
                return -4;
            }
            else {
                return super.move(c, w, map);
            }
        }
    }

}
