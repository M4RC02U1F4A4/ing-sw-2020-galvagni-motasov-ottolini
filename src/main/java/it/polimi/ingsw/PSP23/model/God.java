package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Athena;

/**
*   God class
*/
public class God {
    private String name;
    protected int remains_moves;
    protected int remains_builds;
    protected int starting_z;
    private boolean is_athena_in_game;
    private Athena athena_player;

    //TODO javadoc, intellij mi dice che non so scrivere tuono...
    public God() {
        setUpGod("zioDelTuono");
    }

    //TODO Jabbadoc
    protected void setUpGod(String godName) {
        this.name = godName;
        this.remains_builds = 0;
        this.remains_moves = 0;
        this.is_athena_in_game = false;
        this.athena_player = null;
    }

    public void startTurn() {
        this.setUpTurn(x,y);
    }

    /**
    *   Set the parameters for how many time the player can move and build, based on the god that he owns
    *   @param x number of moves that the player can do
    *   @param y number of times that the player can build
    */
    public void setUpTurn(int x, int y) {
        this.remains_moves = x;
        this.remains_builds = y;
        this.starting_z = -1;
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
            if ((c.isNear(w)) && !c.isOccupied()) {
                if (0 < remains_moves) {
                    if (-1 == this.starting_z)
                        this.starting_z = w.getPosZ();
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
            if (c.isNear(w)) {
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
    *   Check if the player has won the game
    */
    public boolean checkWin(Worker w) {
        return (0 < this.starting_z) && (3 > this.starting_z) && (3 == w.getPosZ());
    }

    /**
    *   Check if the <code>Athena</code> god is used by some player in this game
    *   @param ap <code>Athena</code> god
    */
    public void AthenaIsHere(Athena ap) {
        is_athena_in_game = true;
        athena_player = ap;
    }

    //TODO javadoc too
    public String choseRandomGod(){
        String gods[]={"Apollo","Artemis",",Athena","Atlas","Demeter","Hephaestus","Minotaur","Pan","Protheus"};
        int i= (int) ((Math.random()*100)%gods.length);
        return gods[i];
    }

}