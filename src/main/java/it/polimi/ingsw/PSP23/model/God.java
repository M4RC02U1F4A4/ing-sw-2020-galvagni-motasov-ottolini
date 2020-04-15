package it.polimi.ingsw.PSP23.model;

/**
*   God class
*/
public class God {
    private String name;
    protected int remains_moves;
    protected int remains_builds;
    private boolean is_athena_in_game;
    private Athena athena_player;

    /**
    *   Set the parameters for how many time the player can move and build, based on the god that he owns
    *   @param x number of moves that the player can do
    *   @param y number of times that the player can build
    */
    public void startTurn(int x, int y) {
        remains_moves = x;
        remains_builds = y;
    }

    /**
    *   Move the worker in the desired cell
    *   If it is possible to move the worker the <code>moveWorker</code> function is called,
    *   otherwise the action is not successful
    *   @param c cell in which the player want to move the worker
    *   @param w worker that the player want to move
    *   @return 0 if the operation is successful, -1 if any type of error has occurred
    */
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

    /**
    *   TODO: javadoc
    */
    public void build(Cell c, Status b, Worker w){
        remains_moves = 0;
        if (0 < remains_builds) {
            if (c.isNear(c, w)) {
                switch (b){
                    case BUILT:c.build(Status.BUILT);break;
                    case CUPOLA:c.build(Status.CUPOLA);break;
                }
                remains_builds--;
            }
            else {
                // TODO: error, too far away
            }
        }
        else {
            //TODO: error, already build
        }
    }

    /**
    *   Check if tha player has won the game
    */
    public void checkWin() {
    }

    /**
    *   Check if the <code>Athena</code> god is used by some player in this game
    *   @param ap <code>Athena</code> god
    */
    public void AthenaIsHere(Athena ap) {
        is_athena_in_game = true;
        athena_player = ap;
    }

}