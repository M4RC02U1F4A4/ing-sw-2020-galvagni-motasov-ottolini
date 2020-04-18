package it.polimi.ingsw.PSP23.model;

/**
*   TurnMagaer class
*/
public class TurnManager {
    public enum Phase{START,MOVE, BUILD,CHECK_WIN, END}
    private static Phase phase=Phase.START;

    private static int turnNumber=0;
    private static int playerNumber;
    private static int currentPlayer=0;

    /**
    *   Costructor 
    *   @param playerNumber number assigned to the player
    */
    public TurnManager(int playerNumber){
        this.playerNumber=playerNumber;
        this.phase=Phase.START;
    }

    /**
    *   reset shifts and start over
    *   Restart with player 0 and from the 1st phase of the turn
    */
    public static void reset(){
        currentPlayer=0;
        phase=Phase.START;
    }

    /**
    *   @return the phase of the turn   
    */
    public static Phase getPhase() {
        return phase;
    }

    /**
    *   @return the number of the player that can play this turn
    */
    public static int getTurnNumber() {
        return turnNumber;
    }

    /**
    *   @return the player number
    */
    public static int getPlayerNumber() {
        return playerNumber;
    }

    /**
    *   @return the player that is playing the turn
    */
    public static int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
    *   @param phase phase that need to be set
    */
    public static void setPhase(Phase phase) {
        TurnManager.phase = phase;
    }

    /**
    *   @param turnNumber set the number of the player that can play this turn
    */
    public static void setTurnNumber(int turnNumber) {
        TurnManager.turnNumber = turnNumber;
    }

    /**
    *   @param playerNumber number to be given to the player
    */
    public static void setPlayerNumber(int playerNumber) {
        TurnManager.playerNumber = playerNumber;
    }

    /**
    *   @param currentPlayer player to be set as current
    */
    public static void setCurrentPlayer(int currentPlayer) {
        TurnManager.currentPlayer = currentPlayer;
    }

    /**
    *   Set the nex phase of the turn
    */
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

    /**
    *   TODO: javadoc
    */
    public static void nextPlayer(){
        turnNumber++;
        if(currentPlayer+1==playerNumber){
            reset();
        }
        else {
            phase=Phase.START;
        }
    }

}
