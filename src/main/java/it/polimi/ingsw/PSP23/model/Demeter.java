package it.polimi.ingsw.PSP23.model;

public class Demeter extends God {
    private int prev_build_x;
    private int prev_build_y;

    @Override
    public void startTurn(int x, int y) {
        super.startTurn(1, 2);
        prev_build_x = -1;
        prev_build_y = -1;
    }

    @Override
    public void build(Cell c, Status b, Worker w) {
        if (0 < remains_builds) {
            if ((c.getX() != prev_build_x)&&(c.getY() != prev_build_y)) {
                super.build(c, Status.BUILT, w);
                prev_build_x = c.getX();
                prev_build_y = c.getY();
            }
            else {
                // TODO error, already build here
            }
        }
        else {
            //TODO error, no more building allowed
        }
    }

}
