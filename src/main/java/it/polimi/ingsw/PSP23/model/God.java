package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Athena;

import java.util.ArrayList;

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

    /**
     *  Constructor of god
     */
    public God() {
        setUpGod("zioDelTuono");
    }

    /**
     * Setup the god for the turn, used only internally. DO NOT USE OUTSIDE!
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
    *   Set the parameters for how many time the player can move and build. DO NOT USE OUTSIDE!
    *   @param move number of moves that the player can do
    *   @param build number of times that the player can build
    *   @param moved_up true if athena moved up in this turn
    */
    protected void setUpTurn(int move, int build, boolean moved_up) {
        this.remains_moves = move;
        this.remains_builds = build;
        this.starting_z = -1;
        this.athena_moved_up = moved_up;
    }

    /**
     *  TODO check worker color for Apollo and Minotaur
    *   Move the worker in the desired cell
    *   If it is possible to move the worker the <code>moveWorker</code> function is called,
    *   otherwise the action is not successful
    *   @param c cell in which the player want to move the worker
    *   @param w worker that the player want to move
    *   @param map used only for minotaur power
    *   @return 0 if the operation is successful,
    *           -1 if not near or occupied,
    *           -2 if already moved this turn,
    *           -3 athena block moved up moves,
    *           -4 (Artemis) not back to origin,
    *           -6 (Apollo) tried to move in friendly occupied cell.
    */
    public int move(Cell c, Worker w, Map map){
        if(this.is_athena_in_game && this.athena_moved_up && (w.getPosZ() < c.height()))
            return -3;
        if (0 == this.remains_moves)
            return -2;
        if (!(c.isNear(w, true)) || (c.isOccupied() && !("Apollo".matches(this.name))))
            return -1;
        if (-1 == this.starting_z)
            this.starting_z = w.getPosZ();
        w.moveWorker(c);
        this.remains_moves--;
        return 0;
    }

    /**
     * call the cell build function based on the god power
     * @param c cell
     * @param b status of the cell
     * @param w worker that the player want to use to build
     * @return  the level built
     *         -1 if cell is not near or is under the worker,
     *         -2 if the player already build in this turn,
     *         -3 (Demeter) if already build in this cell this turn,
     *         -4 (Hephaestus) if is a different building slot,
     *         -5 (Hesta) if perimetral slot build
     */
    public int build(Cell c, Status b, Worker w){
        int level = 0;
        this.remains_moves = 0;
        if (0 == this.remains_builds)
            return -2;
        if (!(c.isNear(w, false)) || ((c == w.getCell()) && !("Zeus".equals(this.name))))
            return -1;
        if ("Atlas".equals(this.name))
            level = c.build(b);
        else
            level = c.build(Status.BUILT);
        this.remains_builds--;
        return level;
    }

    /**
    *   Check if the player has won the game
    *   @param w the worker moved is needed
    */
    public boolean checkWin(Worker w) {
        if ((0 < this.starting_z) && (3 > this.starting_z) && (3 == w.getPosZ())) {
            if ((this.is_hera_in_game) && !("Hera".matches(this.name))){
                return (0 != w.getPosX()) && (0 != w.getPosY()) && (4 != w.getPosX()) && (4 != w.getPosY());
            }
            else
                return true;
        }
        else
            return false;
    }

    /**
     *  TODO add color check for apollo e minotaur
     *  Used for check if a worker cannot move thus triggering a loss
     *  specific cases: Apollo, Athena, Minotaur
     * @param w the worker checked
     * @param map to check the cell near the worker
     * @return true if loss, false if a move is possible
     */
    public boolean checkLossMove(Worker w, Map map) {
        int posX = w.getPosX();
        int posY = w.getPosY();
        for (int cont1 = -1; cont1 < 2; cont1++) {
            for (int cont2 = -1; cont2 < 2; cont2++) {
                Cell ILikeToMoveIt = map.getCell(posX + cont1, posY + cont2);
                if (ILikeToMoveIt.isNear(w,true) && !((posX == ILikeToMoveIt.getX()) && (posY == ILikeToMoveIt.getY()))) {
                    if (!(ILikeToMoveIt.isOccupied()) || ((("Apollo".equals(this.name))) || ("Minotaur".equals(this.name)))) {
                        if (!(this.athena_moved_up && w.getPosZ() < ILikeToMoveIt.height())) {
                            if ("Minotaur".equals(this.name)) {
                                int X = (posX + 2 * cont1);
                                int Y = (posY + 2 * cont2);
                                if (X>=0 && X<5 && Y>=0 && Y<5) {
                                    if (!map.getCell(X,Y).isOccupied())
                                        return false;
                                }
                            }
                            else
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     *  Used for check if a worker cannot build thus triggering a loss
     *  specific cases: Zeus
     * @param w the worker checked
     * @param map to check the cell near the worker
     * @return true if loss, false if a build is possible
     */
    public boolean checkLossBuild(Worker w, Map map) {
        int posX = w.getPosX();
        int posY = w.getPosY();
        for (int cont1 = -1; cont1 < 2; cont1++) {
            for (int cont2 = -1; cont2 < 2; cont2++) {
                Cell JustChecking = map.getCell(posX + cont1, posY + cont2);
                if (JustChecking.isNear(w,false) && !JustChecking.isOccupied() && JustChecking.height() < 4)
                    return false;
                else if ("Zeus".equals(this.name) && 0 == cont1 && 0 == cont2 && JustChecking.height() < 3)
                    return false;
            }
        }
        return true;
    }

    /**
    *   Set the flag for Athena's power
    */
    public void AthenaIsHere() {
        this.is_athena_in_game = true;
    }

    /**
     *  Used by turn manager
     * @return if athena moved up
     */
    public boolean AthenaMovedUp() {
        return this.athena_moved_up;
    }

    /**
     *  Set the flag for Hera's power
     */
    public void HeraIsHere() {
        this.is_hera_in_game = true;
    }

    /**
     *  Getter function for the name
     * @return the name of the god used
     */
    public String getName() {
        return this.name;
    }

    /**
     * TODO update documentation
     * @return name of the god
     */
    public static ArrayList<String> getAllGods(){
        ArrayList<String> gods=new ArrayList<>(); //={"Apollo","Artemis",",Athena","Atlas","Chronus","Demeter","Hephaestus","Hera","Hestia","Minotaur","Pan","Prometheus","Triton","Zeus"};
        gods.add("Apollo");
        gods.add("Artemis");
        gods.add("Athena");
        gods.add("Atlas");
        gods.add("Chronus");
        gods.add("Demeter");
        gods.add("Hephaestus");
        gods.add("Hera");
        gods.add("Hestia");
        gods.add("Minotaur");
        gods.add("Pan");
        gods.add("Prometheus");
        gods.add("Triton");
        gods.add("Zeus");
        return gods;
    }
}