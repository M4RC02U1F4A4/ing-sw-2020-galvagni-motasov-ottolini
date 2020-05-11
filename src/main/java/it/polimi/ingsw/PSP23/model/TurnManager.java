package it.polimi.ingsw.PSP23.model;

/**
*   TurnMagaer class
*/
public class TurnManager {
    enum Phase{CHOOSE_WORKER, MOVE, BUILD, END}
    private int numberOfPlayers=0;
    private int currentPlayer;
    private Phase currentPhase;

    public TurnManager(){
        currentPlayer=0;
        currentPhase=Phase.CHOOSE_WORKER;
    }

    public void addPlayer(){
        numberOfPlayers++;
    }

    public void nextPhase(){
        switch (currentPhase){
            case CHOOSE_WORKER:{
                    currentPhase=Phase.MOVE;
                    break;
            }
            case MOVE:{
                currentPhase=Phase.BUILD;
                break;
            }
            case BUILD:{
                currentPhase=Phase.END;
                break;
            }
            case END:{
                currentPhase=Phase.CHOOSE_WORKER;
                currentPlayer++;
                if(numberOfPlayers==currentPlayer){
                    currentPlayer=0;
                }
            }
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }


}
