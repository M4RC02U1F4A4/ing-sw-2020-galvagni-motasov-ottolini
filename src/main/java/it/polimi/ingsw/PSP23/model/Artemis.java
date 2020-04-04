package it.polimi.ingsw.PSP23.model;

public class Artemis extends God {
    private int starting_x;
    private int starting_y;

    @Override
    public void startTurn(int x, int y) {
        super.startTurn(2, 1);
        starting_x = -1;
        starting_y = -1;
    }

    @Override
    public int move(Cell c, Worker w) {
        if ((-1 == starting_y)&&(-1 == starting_x)) {
            starting_x = w.getPosX();
            starting_y = w.getPosY();
            return super.move(c, w);
        }
        else {
            if ((c.getY() == starting_y)&&(c.getX() == starting_x)) {
                return -1;
                // error, invalid move
            }
            else {
                return super.move(c, w);
            }
        }
    }

}
