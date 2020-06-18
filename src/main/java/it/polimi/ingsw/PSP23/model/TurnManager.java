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
    private boolean skipBuild;
    private boolean skipMove;
    private boolean resultsTime;

    public TurnManager() {
        currentPlayerNumber = 0;
        currentPhase = Phase.GOD_CHOOSE;
        AthenaMovedUp = false;
        AthenaPlayer = -1;
        resultsTime = false;
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
            case CHOOSE_WORKER: {
                currentPhase = Phase.START_TURN;
                currentPlayer.getGod().startTurn(AthenaMovedUp);
                break;
            }
            case START_TURN: {
                skipBuild = false;
                skipMove = false;
                if ("Prometheus".equals(getCurrentGod().getName()))
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else if ("Chronus".equals(getCurrentGod().getName()))
                    currentPhase = Phase.CHECK_WIN;
                else
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                break;
            }
            case CHECK_WIN: {
                if (resultsTime)
                    currentPhase = Phase.GOOD_NEWS;
                else
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                break;
            }
            case CHECK_LOSE_MOVE: {
                if (resultsTime)
                    currentPhase = Phase.BAD_NEWS;
                else
                    currentPhase = Phase.MOVE;
                break;
            }
            case MOVE:
                currentPhase = Phase.CHECK_WIN_MOVE;
                break;
            case CHECK_WIN_MOVE: {
                if (resultsTime)
                    currentPhase = Phase.GOOD_NEWS;
                else if ((2 == getCurrentGod().remains_moves))
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                else if ((1 == getCurrentGod().remains_moves) && !(skipMove))
                    currentPhase = Phase.CHECK_LOSE_MOVE;
                else
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                break;
            }
            case CHECK_LOSE_BUILD: {
                if (resultsTime)
                    currentPhase = Phase.BAD_NEWS;
                else
                    currentPhase = Phase.BUILD;
                break;
            }
            case BUILD:
                currentPhase = Phase.CHECK_WIN_BUILD;
                break;
            case CHECK_WIN_BUILD: {
                if (resultsTime)
                    currentPhase = Phase.GOOD_NEWS;
                else if ((2 == getCurrentGod().remains_builds))
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else if ((1 == getCurrentGod().remains_builds) && !(skipBuild))
                    currentPhase = Phase.CHECK_LOSE_BUILD;
                else
                    currentPhase = Phase.END;
                break;
            }
            case GOOD_NEWS:
                break;
            case BAD_NEWS: {
                currentPhase = Phase.CHOOSE_WORKER;
                currentPlayerNumber++;
                if (numberOfPlayers <= currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
            }
            case END: {
                if (AthenaPlayer == currentPlayerNumber)
                    AthenaMovedUp = getCurrentGod().AthenaMovedUp();
                currentPhase = Phase.CHOOSE_WORKER;
                currentPlayerNumber++;
                if (numberOfPlayers <= currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
            }
        }
    }

    /**
     * getter
     * @return the current player god
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
     * remove the athena player
     */
    public void removeAthena() {
        AthenaPlayer = -1;
        AthenaMovedUp = false;
    }

    /**
     * setter
     */
    public void setResults() {
        resultsTime = true;
    }

    /**
     * setter
     */
    public void setSkipMove() {
        skipMove = true;
    }

    /**
     * setter
     */
    public void setSkipBuild() {
        skipBuild = true;
    }

    //TEST ONLY!
    public void goBanana(){
        currentPhase=Phase.CHOOSE_WORKER;
    }

}