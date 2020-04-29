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
    protected boolean is_hera_in_game;
    private boolean is_athena_in_game;
    private boolean athena_moved_up;

    //TODO javadoc, intellij mi dice che non so scrivere tuono...
    public God() {
        setUpGod("zioDelTuono");
    }

    /**
     * God setup
     * @param godName name of the god
     */
    protected void setUpGod(String godName) {
        this.name = godName;
        this.remains_builds = 0;
        this.remains_moves = 0;
        this.starting_z = -2;
        this.is_hera_in_game = false;
        this.is_athena_in_game = false;
        this.athena_moved_up = false;
    }

    /**
     * call the function <code>setUpTurn</code> with the parameters that indicate
     * the number of moves and builds that the player can do
     * @param moved_up indicate if athena moved up this turn
     */
    public void startTurn(boolean moved_up) {
        this.setUpTurn(1,1, moved_up);
    }

    /**
    *   Set the parameters for how many time the player can move and build, based on the god that he owns
    *   @param x number of moves that the player can do
    *   @param y number of times that the player can build
    *   @param moved_up true if athena moved up in this turn
    */
    public void setUpTurn(int x, int y, boolean moved_up) {
        this.remains_moves = x;
        this.remains_builds = y;
        this.starting_z = -1;
        this.athena_moved_up = moved_up;
    }

    /**
    *   Move the worker in the desired cell
    *   If it is possible to move the worker the <code>moveWorker</code> function is called,
    *   otherwise the action is not successful
    *   @param c cell in which the player want to move the worker
    *   @param w worker that the player want to move
    *   @return 0 if the operation is successful,
    *           -1 if not near or occupied,
    *           -2 if already moved this turn,
    *           -3 athena block moved up moves,
    *           -4 (Artemis) not back to origin,
    *           -5 (Prometheus) tried to moved up after build
    */
    public int move(Cell c, Worker w){
        // verifico che non si salga se si verifica il potere di athena
        if(this.is_athena_in_game && this.athena_moved_up && (w.getPosZ() < c.height())) {
            return -3;
        }
        else {
            if ((c.isNear(w, true)) && !c.isOccupied()) {
                if (0 < this.remains_moves) {
                    if (-1 == this.starting_z)
                        this.starting_z = w.getPosZ();
                    w.moveWorker(c);
                    this.remains_moves--;
                    return 0;
                }
                else {
                    return -2;
                }
            }
            else {
                return -1;
            }
        }
    }

    /**
     * call the cell build function based on the god power
     * @param c cell
     * @param b status of the cell
     * @param w worker that the player want to use to build
     * @return -1 if cell is not near or is under the worker,
     *         -2 if the player already build in this turn,
     *         -3 (Demeter) if already build in this cell this turn,
     *         -4 (Hephaestus) if is a different building slot,
     *         -5 (Hesta) if perimetral slot build
     */
    public int build(Cell c, Status b, Worker w){
        this.remains_moves = 0;
        if (0 < this.remains_builds) {
            if ((c.isNear(w, false)) && ((c != w.getCell()) || ("Zeus".equals(this.name)))) {
                switch (b){
                    case BUILT:c.build(Status.BUILT);break;
                    case CUPOLA:c.build(Status.CUPOLA);break;
                }
                this.remains_builds--;
                return 0;
            }
            else {
                return -1;
            }
        }
        else {
            return -2;
        }
    }

    /**
    *   Check if the player has won the game
    *   @param w the worker moved is needed
    */
    public boolean checkWin(Worker w) {
        if ((0 < this.starting_z) && (3 > this.starting_z) && (3 == w.getPosZ())) {
            if (this.is_hera_in_game){
                return (0 != w.getPosX()) && (0 != w.getPosY()) && (4 != w.getPosX()) && (4 != w.getPosY());
            }
            else
                return true;
        }
        else
            return false;
    }

    /**
    *   Check if the <code>Athena</code> god is used by some player in this game
    */
    public void AthenaIsHere() {
        this.is_athena_in_game = true;
    }

    //TODO javadoc
    public void HeraIsHere() {
        this.is_hera_in_game = true;
    }

    /**
     * extracts a random god
     * @return name of the god
     */
    public String choseRandomGod(){
        String gods[]={"Apollo","Artemis",",Athena","Atlas","Chronus","Demeter","Hephaestus","Hera","Hestia","Minotaur","Pan","Prometheus","Triton","Zeus"};
        int i= (int) ((Math.random()*100)%gods.length);
        return gods[i];
    }

    // Metodo utilizzato solo per i test TODO javadoc
    public boolean AthenaMovedUp() {
        return this.athena_moved_up;
    }

    // Metodo utilizzato solo per i test TODO javadoc
    public String name() {
        return this.name;
    }
}