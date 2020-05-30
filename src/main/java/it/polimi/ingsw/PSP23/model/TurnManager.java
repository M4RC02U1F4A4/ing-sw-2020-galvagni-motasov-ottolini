package it.polimi.ingsw.PSP23.model;

/**
 *   TurnManager class
 */
public class TurnManager {
    private int numberOfPlayers = 0; // turn manager work with number 0, 1 and 2
    private int currentPlayerNumber;
    private Player currentPlayer;
    private Phase currentPhase;
    private int AthenaPlayer;
    private boolean AthenaMovedUp;
    private boolean skipEnabled;

    public TurnManager() {
        currentPlayerNumber = 0;
        currentPhase = Phase.GOD_CHOOSE;
        AthenaMovedUp = false;
        AthenaPlayer = -1;
    }

    /**
     * Game setup.
     * This function relies on Gods and Workers being initialized to null.
     * END will be seen as the first state for every player.
     * Will set the phase to CHOOSE_WORKER when finished.
     * Also initialize athena and hera flag in gods.
     */
    public void nextPhaseSetUp() {
        switch (currentPhase) {
            case GOD_CHOOSE:
                currentPlayerNumber = 1;
                currentPhase = Phase.END;
                break;
            case GOD_PICK:
                if (0 == currentPlayerNumber)
                    currentPhase = Phase.WORKER_HOUSING;
                else {
                    currentPlayerNumber++;
                    if (numberOfPlayers <= currentPlayerNumber)
                        currentPlayerNumber = 0;
                    currentPhase = Phase.END;
                }
                break;
            case WORKER_HOUSING:
                currentPlayerNumber++;
                if (numberOfPlayers == currentPlayerNumber)
                    currentPlayerNumber = 0;
                currentPhase = Phase.END;
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
    public void nextPhaseGame() {
        switch (currentPhase) {
            case CHOOSE_WORKER:
                currentPhase = Phase.START_TURN;
                currentPlayer.getGod().startTurn(AthenaMovedUp);
                break;
            case START_TURN:
                skipEnabled = false;
                if ("Prometheus".equals(getCurrentGod().getName()) && 2 == getCurrentGod().remains_builds)
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else if ("Chronus".equals(getCurrentGod().getName()))
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
                if ((1 <= getCurrentGod().remains_moves) && !(getCurrentGod().getSkip())) {
                    if ("Triton".equals(getCurrentGod().getName()) || "Artemis".equals(getCurrentGod().getName()))
                        skipEnabled = true;
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                }
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
                if ((2 <= getCurrentGod().remains_builds) && !(getCurrentGod().getSkip())) {
                    if ("Demeter".equals(getCurrentGod().getName()) || "Hephaestus".equals(getCurrentGod().getName()) ||
                    "Hestia".equals(getCurrentGod().getName()) || "Prometheus".equals(getCurrentGod().getName()))
                        skipEnabled = true;
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                }
                else
                    currentPhase = Phase.END;
                break;
            case END:
                if (AthenaPlayer == currentPlayerNumber)
                    AthenaMovedUp = getCurrentGod().AthenaMovedUp();
                currentPhase = Phase.CHOOSE_WORKER;
                currentPlayerNumber++;
                if (numberOfPlayers <= currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
        }
    }

    /**
     * TODO: javadoc
     * @return
     */
    private God getCurrentGod() {
        return currentPlayer.getGod();
    }

    /**
     * Used to set number of players
     */
    public void setPlayerNumber(int num){
        numberOfPlayers = num;
    }

    /**
     * this function initialize the turn of the player
     * also reset the athena moveup if athena is in game
     * @param VamosAllaPlayer i Choose you VAMOSALLAPLAYER!
     */
    public void setCurrentPlayer(Player VamosAllaPlayer) {
        currentPlayer = VamosAllaPlayer;
        if (null != VamosAllaPlayer.getGod()) {
            if ("Athena".equals(VamosAllaPlayer.getGod().getName())) {
                AthenaPlayer = currentPlayerNumber;
                AthenaMovedUp = false;
            }
        }
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

    /**
     * getter
     * @return skip enable, the flag to see if the skip move is possible
     */
    public boolean getSkip() {
        return skipEnabled;
    }

    //TEST ONLY!
    public void goBanana(){
        currentPhase=Phase.CHOOSE_WORKER;
    }

}