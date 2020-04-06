package it.polimi.ingsw.PSP23.model;

public class TurnManager {
    public enum Phase{START,MOVE, BUILD,CHECK_WIN, END}
    private static Phase phase=Phase.START;

    private static int turnNumber=0;
    private static int playerNumber;
    private static int currentPlayer=0;

    public TurnManager(int playerNumber){
        this.playerNumber=playerNumber;
        this.phase=Phase.START;
    }

    public static void reset(){
        currentPlayer=0;
        phase=Phase.START;
    }

    public static Phase getPhase() {
        return phase;
    }

    public static int getTurnNumber() {
        return turnNumber;
    }

    public static int getPlayerNumber() {
        return playerNumber;
    }

    public static int getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setPhase(Phase phase) {
        TurnManager.phase = phase;
    }

    public static void setTurnNumber(int turnNumber) {
        TurnManager.turnNumber = turnNumber;
    }

    public static void setPlayerNumber(int playerNumber) {
        TurnManager.playerNumber = playerNumber;
    }

    public static void setCurrentPlayer(int currentPlayer) {
        TurnManager.currentPlayer = currentPlayer;
    }

    public static void nextPhase(){
        switch (phase){
            case START:{
                phase=Phase.MOVE;
                break;
            }
            case MOVE:{
                phase=Phase.BUILD;
                break;
            }
            case BUILD:{
                phase=Phase.CHECK_WIN;
                break;
            }
            case CHECK_WIN:{
                phase=Phase.END;
                break;
            }
            case END:{
                nextPlayer();
            }
        }
    }

    public static void nextPlayer(){
        //TODO: vedere se usare le liste oppure se si può farne a meno
    }

}
