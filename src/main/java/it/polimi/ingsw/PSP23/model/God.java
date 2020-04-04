package it.polimi.ingsw.PSP23.model;

public class God {
    private String name;
    protected int remains_moves;
    protected int remains_builds;
    private boolean is_athena_in_game;
    private Athena athena_player;

    public void startTurn(int x, int y) {
        remains_moves = x;
        remains_builds = y;
    }

    public int move(Cell c, Worker w){
        // verifico che non si salga se si verifica il potere di athena
        if(is_athena_in_game && athena_player.CheckMovedUp() && (w.getPosZ() < c.height())) {
            return -1;
            //error, invalid move
        }
        else {
            if ((c.isNear(c, w)) && !c.isOccupied()) {
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
                //error, invalid move
            }
        }
    }

    public void build(Cell c, Status b, Worker w){
        remains_moves = 0;
        if (0 < remains_builds) {
            if (c.isNear(c, w)) {
                c.build(Status.BUILT);
                remains_builds--;
            }
            else {
                // TODO error, too far away
            }
        }
        else {
            //TODO error, already build
        }
    }

    public void checkWin() {
    }

    public void AthenaIsHere(Athena ap) {
        is_athena_in_game = true;
        athena_player = ap;
    }

}