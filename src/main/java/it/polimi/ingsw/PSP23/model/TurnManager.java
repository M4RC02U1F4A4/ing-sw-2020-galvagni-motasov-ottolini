package it.polimi.ingsw.PSP23.model;

/**
*   TurnManager class
*/
public class TurnManager {
    private int numberOfPlayers = 0;
    private int currentPlayerNumber;
    private Player currentPlayer;
    private Phase currentPhase;
    private int AthenaPlayer;
    private int HeraPlayer;
    private boolean Athena_moved_up;

    public TurnManager(){
        this.currentPlayerNumber = 0;
        this.currentPhase = Phase.GOD_CHOOSE;
        this.Athena_moved_up = false;
        this.AthenaPlayer = -1;
        this.HeraPlayer = -1;
    }

    /**
     * Game setup.
     * This function relies on Gods and Workers being initialized to null.
     * END will be seen as the first state for every player.
     * Will set the phase to CHOOSE_WORKER when finished.
     * Also initialize athena and hera flag in gods.
     */
    public void nextPhaseSetUp(){
        switch (currentPhase) {
            case GOD_CHOOSE:
                this.currentPlayerNumber = 1;
                currentPhase = Phase.END;
                break;
            case GOD_PICK:
                if (0 == currentPlayerNumber)
                    currentPhase = Phase.WORKER_HOUSING;
                else {
                    this.currentPlayerNumber++;
                    if (numberOfPlayers == this.currentPlayerNumber)
                        this.currentPlayerNumber = 0;
                    currentPhase = Phase.END;
                }
                break;
            case WORKER_HOUSING:
                this.currentPlayerNumber++;
                if (numberOfPlayers == this.currentPlayerNumber)
                    this.currentPlayerNumber = 0;
                currentPhase = Phase.END;
                if (-1 != this.AthenaPlayer)
                    this.currentPlayer.getGod().AthenaIsHere();
                if (-1 != this.HeraPlayer)
                    this.currentPlayer.getGod().HeraIsHere();
                break;
            case END:
                if (null == currentPlayer.getGod())
                    currentPhase = Phase.GOD_PICK;
                else if (null == currentPlayer.getWorkerByNumber(0) || null == currentPlayer.getWorkerByNumber(1))
                    currentPhase = Phase.WORKER_HOUSING;
                else
                    currentPhase = Phase.CHOOSE_WORKER;
                break;
        }
    }

    /**
     * Game phase switcher, also initialize the turn for gods.
     * When END is seen it has to be used one more time.
     */
    public void nextPhaseGame(){
        switch (currentPhase) {
            case CHOOSE_WORKER:
                currentPhase = Phase.START_TURN;
                currentPlayer.getGod().startTurn(Athena_moved_up);
                break;
            case START_TURN:
                if ("Prometheus".equals(currentPlayer.getGod().getName()) && 2 == currentPlayer.getGod().remains_builds)
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else if ("Chronus".equals(currentPlayer.getGod().getName()))
                    currentPhase = Phase.CHECK_WIN;
                else
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                break;
            case CHECK_WIN:
                currentPhase = Phase.CHECK_LOSE_MOVE;
                break;
            case CHECK_LOSE_MOVE:
                currentPhase = Phase.MOVE;
                break;
            case MOVE:
                currentPhase = Phase.CHECK_WIN_MOVE;
                break;
            case CHECK_WIN_MOVE:
                if (1 <= currentPlayer.getGod().remains_moves)
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                else
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                break;
            case CHECK_LOSE_BUILD:
                currentPhase = Phase.BUILD;
                break;
            case BUILD:
                currentPhase = Phase.CHECK_WIN_BUILD;
                break;
            case CHECK_WIN_BUILD:
                if (2 <= currentPlayer.getGod().remains_builds)
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else
                    currentPhase = Phase.END;
                break;
            case END:
                if (this.AthenaPlayer == currentPlayerNumber)
                    this.Athena_moved_up = currentPlayer.getGod().AthenaMovedUp();
                currentPhase = Phase.CHOOSE_WORKER;
                currentPlayerNumber++;
                if (numberOfPlayers == currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
        }
    }

    /**
     * Used to add players in the beginning
     */
    public void addPlayer(){
        numberOfPlayers++;
    }

    /**
     * Used to remove player after loss in 3 player mode
     */
    public void subsPlayer(){
        numberOfPlayers--;
    }

    /**
     * this function initialize the turn of the player
     * also reset the athena moveup if athena is in game
     * @param VamosAllaPlayer i Choose you VAMOSALLAPLAYER!
     */
    public void setCurrentPlayer(Player VamosAllaPlayer) {
        this.currentPlayer = VamosAllaPlayer;
        if (null == currentPlayer.getGod());
        else if ("Athena".equals(VamosAllaPlayer.getGod().getName())) {
            this.AthenaPlayer = this.currentPlayerNumber;
            this.Athena_moved_up = false;
        }
        else if ("Hera".equals(VamosAllaPlayer.getGod().getName()))
            this.HeraPlayer = this.currentPlayerNumber;
    }

    /**
     * @return the turn player number
     */
    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    /**
     * @return the number of players in the game right now
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * @return the current phase of the turn
     */
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    //SOLO DI DEBUG!
    public void vaiAllaFineDelTurno(){
        currentPhase=Phase.END;
    }

    //TEST ONLY!
    public void goBanana(){
        currentPhase=Phase.CHOOSE_WORKER;
    }
}
