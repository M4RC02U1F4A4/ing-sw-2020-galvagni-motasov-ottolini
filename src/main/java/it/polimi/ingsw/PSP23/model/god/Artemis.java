package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;

public class Artemis extends God {
    private int starting_x;
    private int starting_y;

    public Artemis() {
        super.setUpGod("Artemis");
        this.starting_x = -1;
        this.starting_y = -1;
    }

    @Override
    public void startTurn(boolean moved_up) {
        super.setUpTurn(2, 1, moved_up);
        this.starting_x = -1;
        this.starting_y = -1;
    }

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
