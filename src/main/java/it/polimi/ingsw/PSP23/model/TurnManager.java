package it.polimi.ingsw.PSP23.model;

/**
 *   TurnManager class
 */
public class TurnManager {
    private int numberOfPlayers = 0; // turn manager work with number 0, 1 and 2
    private int currentPlayerNumber;
    private Player currentPlayer;
    private Phase currentPhase;
    private boolean skipBuild;
    private boolean skipMove;
    private boolean resultsTime;

    public TurnManager() {
        currentPlayerNumber = 0;
        currentPhase = Phase.GOD_CHOOSE;
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
            case CHOOSE_WORKER:
                currentPhase = Phase.START_TURN;
                break;
            case START_TURN: {
                skipBuild = false;
                skipMove = false;
                switch (getCurrentGod().getName()) {
                    case "Prometheus":
                        currentPhase = Phase.CHECK_LOSE_BUILD;
                        break;
                    case "Chronus":
                        currentPhase = Phase.CHECK_WIN;
                        break;
                    default:
                        currentPhase = Phase.CHECK_LOSE_MOVE;
                        break;
                }
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
                if (!resultsTime) {
                    switch (getCurrentGod().remains_moves) {
                        case 2:
                            currentPhase = Phase.CHECK_LOSE_MOVE;
                            break;
                        case 1: {
                            if (skipMove)
                                currentPhase = Phase.CHECK_LOSE_BUILD;
                            else
                                currentPhase = Phase.CHECK_LOSE_MOVE;
                            break;
                        }
                        default: {
                            if (getCurrentGod().remains_builds > 0)
                                currentPhase = Phase.CHECK_LOSE_BUILD;
                            else
                                currentPhase = Phase.END;
                            break;
                        }
                    }
                }
                else
                    currentPhase = Phase.GOOD_NEWS;
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
                if (!resultsTime) {
                    switch (getCurrentGod().remains_builds) {
                        case 2: {
                            if (skipBuild) {
                                currentPhase = Phase.CHECK_LOSE_MOVE;
                                skipBuild = false;
                            }
                            else
                                currentPhase = Phase.CHECK_LOSE_BUILD;
                            break;
                        }
                        case 1: {
                            if ((1 == getCurrentGod().remains_moves) && ("Prometheus".equals(getCurrentGod().getName())))
                                currentPhase = Phase.CHECK_LOSE_MOVE;
                            else if (skipBuild)
                                currentPhase = Phase.END;
                            else
                                currentPhase = Phase.CHECK_LOSE_BUILD;
                            break;
                        }
                        default:
                            currentPhase = Phase.END;
                    }
                }
                else
                    currentPhase = Phase.GOOD_NEWS;
                break;
            }
            case BAD_NEWS:{
                currentPhase = Phase.CHOOSE_WORKER;
                resultsTime = false;
                if (numberOfPlayers <= currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
            }
            case END: {
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
     * @param num number of players
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
     * set the flag to see the results
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

    /**
     * getter
     * @return skipmove
     */
    public boolean getSkipMove() {
        return skipMove;
    }

    /**
     * getter
     * @return skipbuild
     */
    public boolean getSkipBuild() {
        return skipBuild;
    }
}