package it.polimi.ingsw.PSP23.model;

/**
*   TurnManager class
*/
public class TurnManager {
    private int numberOfPlayers = 0;
    private int currentPlayerNumber;
    private Player currentPlayer;
    private Phase currentPhase;
    private int Atheplayer;
    private boolean Athena_moved_up;

    public TurnManager(){
        this.currentPlayerNumber = 0;
        this.currentPhase = Phase.CHOOSE_WORKER;
        this.Athena_moved_up = false;
        this.Atheplayer = -1;
    }

    /**
     * Phase switcher, also a turn initializer for gods
     */
    public void nextPhase(){
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
                if (this.Atheplayer == currentPlayerNumber)
                    this.Athena_moved_up = currentPlayer.getGod().AthenaMovedUp();
                currentPhase = Phase.CHOOSE_WORKER;
                currentPlayerNumber++;
                if(numberOfPlayers == currentPlayerNumber)
                    currentPlayerNumber = 0;
                break;
        }
    }

    public void addPlayer(){
        numberOfPlayers++;
    }

    public void subsPlayer(){
        numberOfPlayers--;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    /**
     * this function initialize the turn of the player
     * also reset the athena moveup if athena is in game
     * @param VamosAllaPlayer i Choose you VAMOSALLAPLAYER!
     */
    public void setCurrentPlayerNumber(Player VamosAllaPlayer) {
        this.currentPlayerNumber = VamosAllaPlayer.getPlayerNumber();
        this.currentPlayer = VamosAllaPlayer;
        if ("Athena".equals(VamosAllaPlayer.getGod().getName())) {
            this.Atheplayer = this.currentPlayerNumber;
            this.Athena_moved_up = false;
        }
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    //SOLO DI DEBUG!
    public void vaiAllaFineDelTurno(){
        currentPhase=Phase.END;
    }
}
