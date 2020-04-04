package it.polimi.ingsw.PSP23.model;

public class Athena extends God {
    private boolean moved_up;

    @Override
    public void startTurn(int x, int y) {
        super.startTurn(1, 2);
    }

    @Override
    public int move(Cell c, Worker w) {
        if ((c.isNear(c, w)) && !c.isOccupied()) {
            if (w.getPosZ() + 1 == c.height()) {
                moved_up = true;
            }
            else {
                moved_up = false;
            }
            if (0 < remains_moves) {
                w.moveWorker(c);
                remains_moves--;
                return 0;
            }
            else {
                return -1;
                // error, no moves left
            }
        }
        else {
            return -1;
            // error, invalid move
        }
    }

    public boolean CheckMovedUp () {
        return moved_up;
    }

}
