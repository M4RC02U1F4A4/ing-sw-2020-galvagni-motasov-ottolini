package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.*;
import it.polimi.ingsw.PSP23.observer.Observable;

import java.util.ArrayList;

public class Game extends Observable<Message> {
    private Map map;
    private Player[] players;
    private String[] availableGods;
    private TurnManager turnManager;
    private boolean ChronusIsHere;
    private int numPlayers, colorVariable, completedTowers, activeWorker;

    public Game(int numPlayer) {
        map=new Map();
        numPlayers = numPlayer;
        players = new Player[numPlayer];
        availableGods = new String[numPlayer];
        turnManager = new TurnManager();
        turnManager.setPlayerNumber(numPlayer);
        ChronusIsHere = false;
        completedTowers = 0;
        colorVariable = 0;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Set functions, game preparation
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * function to add player to game e to turn manager
     * @param name name of the player
     * @param ip ip of the player
     * @return 0 if all ok
     *        -1 if try to make a 4 player game
     */
    public int addPlayer(String name, String ip) {
        if (colorVariable >= numPlayers)
            return -1;
        players[colorVariable] = new Player(name, ip);
        players[colorVariable].setColor(getColor());
        players[colorVariable].setPlayerNumber(colorVariable);
        colorVariable++;
        return 0;
    }

    /**
     * the 2 or 3 gods choosed for this game
     * @param god1 first god
     * @param god2 second god
     * @param god3 third god, will skip if numplayer == 2
     */
    public void godChoose(String god1, String god2, String god3) {
        availableGods[0] = god1;
        availableGods[1] = god2;
        if (3 == numPlayers)
            availableGods[2] = god3;
        nextGameSetUpPhase();
    }

    // TODO così può dare null point exception, trovare altro modo per inizializzare athena e hera
    /**
     * Set the god choosed to 'scelto' then create the god for player
     * @param god is the god choosed by the player
     * @return 0 if everithing is ok
     *        -1 if tried to choose a god not avaiable
     */
    public int setGod(String god) {
        if (god.equals(availableGods[0]))
            availableGods[0] = "Scelto";
        else if (god.equals(availableGods[1]))
            availableGods[1] = "Scelto";
        else if (3 == numPlayers) {
            if (god.equals(availableGods[2]))
                availableGods[2] = "Scelto";
            else
                return -1;
        }
        else
            return -1;
        switch (god) {
            case "Apollo":
                getCurrentPlayer().setGod(new Apollo());
                break;
            case "Artemis":
                getCurrentPlayer().setGod(new Artemis());
                break;
            case "Athena":
                getCurrentPlayer().setGod(new Athena());
                /*for (int cont = 0; cont < numPlayers; cont++)
                    players[cont].getGod().AthenaIsHere();*/
                break;
            case "Atlas":
                getCurrentPlayer().setGod(new Atlas());
                break;
            case "Chronus":
                getCurrentPlayer().setGod(new Chronus());
                ChronusIsHere = true;
                break;
            case "Demeter":
                getCurrentPlayer().setGod(new Demeter());
                break;
            case "Hephaestus":
                getCurrentPlayer().setGod(new Hephaestus());
                break;
            case "Hera":
                getCurrentPlayer().setGod(new Hera());
                /*for (int cont = 0; cont < numPlayers; cont++)
                    players[cont].getGod().HeraIsHere();*/
                break;
            case "Hestia":
                getCurrentPlayer().setGod(new Hestia());
                break;
            case "Minotaur":
                getCurrentPlayer().setGod(new Minotaur());
                break;
            case "Pan":
                getCurrentPlayer().setGod(new Pan());
                break;
            case "Prometheus":
                getCurrentPlayer().setGod(new Prometheus());
                break;
            case "Triton":
                getCurrentPlayer().setGod(new Triton());
                break;
            case "Zeus":
                getCurrentPlayer().setGod(new Zeus());
                break;
        }
        nextGameSetUpPhase();
        return 0;
    }

    //TODO aggiungere un controllo alla cella, se è occupata -> errore
    private int setWorker(int x, int y) {
        if (map.getCell(x, y).isOccupied())
            return -1;
        getCurrentPlayer().placeWorker(map.getCell(x, y));
        nextGameSetUpPhase();
        return 0;
    }

    private void nextGameSetUpPhase() {
        turnManager.nextPhaseSetUp();
        if (Phase.END == turnManager.getCurrentPhase()) {
            turnManager.setCurrentPlayer(getCurrentPlayer());
            turnManager.nextPhaseSetUp();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Set functions, game
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setActiveWorker(int w) {
        activeWorker = w;
        nextGamePhase();
    }

    /**
     * do the build
     * @param x of the cell target
     * @param y of the cell target
     * @param b type of block build
     */
    private void build(int x, int y, Status b) {
        int i = getCurrentGod().build(map.getCell(x, y), b, getActiveWorker());
        if (ChronusIsHere && 4 == i)
            completedTowers++;
        nextGamePhase();
    }

    /**
     * do the move
     * @param x of the cell target
     * @param y of the cell target
     */
    private void move(int x, int y) {
        getCurrentGod().move(map.getCell(x, y), getActiveWorker(), map);
        nextGamePhase();
    }

    // TODO check if is an avaiable move, then skip it and modify the remainmoves/builds id needed
    private void skipAction() {
        switch (getCurrentGod().getName()) {
            case "Artemis":
            case "Demeter":
            case "Hephaestus":
            case "Hestia":
            case "Prometheus":
            case "Triton":
                getCurrentGod().setSkip();
                nextGamePhase();
                break;
        }
    }

    // da chiamare solamente quando si è in 3 giocatori ed uno viene eliminato
    private void removePlayer(int playerNum) {
        switch(playerNum) {
            case 0:
                numPlayers = 2;
                turnManager.setPlayerNumber(2);
                players[0] = players[1];
                players[1] = players[2];
                break;
            case 1:
                numPlayers = 2;
                turnManager.setPlayerNumber(2);
                players[1] = players[2];
                break;
            case 2:
                numPlayers = 2;
                turnManager.setPlayerNumber(2);
                break;
        }
    }

    // TODO check end case
    private void nextGamePhase() {
        turnManager.nextPhaseGame();
        switch (turnManager.getCurrentPhase()) {
            case START_TURN:
                sendMapUpdate();
                nextGamePhase();
                break;
            case CHECK_WIN:
            case CHECK_WIN_MOVE:
            case CHECK_WIN_BUILD:
                if (getCurrentGod().checkWin(getActiveWorker(), completedTowers))
                    sendWin(getCurrentPlayer());
                nextGamePhase();
                break;
            case CHECK_LOSE_MOVE:
                if (getCurrentGod().checkLossMove(getActiveWorker(), map)) {
                    sendLoss(getCurrentPlayer());
                    if (3 == numPlayers)
                        removePlayer(getCurrentPlayerNum());
                    // TODO aggiungere messaggio di vittoria per l'altro giocatore
                }
                nextGamePhase();
                break;
            case CHECK_LOSE_BUILD:
                if (getCurrentGod().checkLossBuild(getActiveWorker(), map)) {
                    sendLoss(getCurrentPlayer());
                    if (3 == numPlayers)
                        removePlayer(getCurrentPlayerNum());
                }
                nextGamePhase();
                break;
            case END:
                nextGamePhase(); // TODO check this
                break;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // internal functions
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Color getColor() {
        switch (colorVariable) {
            case (0):
                return Color.RED;
            case (1):
                return Color.BLUE;
            case (2):
                return Color.WHITE;
            default:
                return null;
        }
    }

    private Worker getActiveWorker() {
        if (0 == activeWorker)
            return getCurrentPlayer().getWorkerByNumber(0);
        else
            return getCurrentPlayer().getWorkerByNumber(1);
    }

    private Player getCurrentPlayer() {
        return players[getCurrentPlayerNum()];
    }

    private God getCurrentGod() {
        return players[getCurrentPlayerNum()].getGod();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // In and Out functions
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Phase getPhase() {
        return turnManager.getCurrentPhase();
    }

    //TODO check this function output
    public int getCurrentPlayerNum() {
        return turnManager.getCurrentPlayerNumber();
    }

    public boolean isPlayerTurn(Player p){
        if(p.getPlayerNumber() == getCurrentPlayerNum())
            return true;
        else
            return false;
    }

    //Please do not use
    public Map getMap() {
        return map;
    }

    public ArrayList<String> getAllGodList() {
        return God.getAllGods();
    }

    // TODO return the list of gods avaiable
    public String getGodList() {
        String tmp="|";
        for(int i=0;i<availableGods.length;i++){
            if(!availableGods[i].equals("Scelto"))
                tmp=tmp+availableGods[i]+"|";
        }
        return tmp;
    }

    /**
     * I MAKE THE GAME DO BLIP BLOP
     * @param action the move requested
     * @param active the active worker (for SELECT_WORKER phase only) 0 for the first, 1 for the second.
     * @param block the block build (for BUILD phase only)
     * @param x the x of the cell build/moved
     * @param y the y of the cell build/moved
     * @return 0 if everything is ok
     *        -1 if there is some errors
     */
    public int performeMove(Action action, Status block, int active, int x, int y) {
        switch (action) {
            case PLACE_WORKER:
                return setWorker(x, y);
            case SELECT_WORKER:
                setActiveWorker(active);
                break;
            case MOVE:
                move(x, y);
                break;
            case BUILD:
                build(x, y, block);
                break;
            case SKIP:
                skipAction();
                break;
        }
        return 1;
    }

    /*
    TODO IVAN tutte queste funzioni sono tue, l'ideale sarebbe lasciare la classe game come istanza del gioco e chiamare
    le funzioni di comunicazione con i player attraverso un'altra classe.
     */

    //Invia il messaggio di partita persa
    public void sendLoss(Player toPlayer) {
        System.out.println("lmao");
    }

    //Invia il messaggio di partita vinta
    public void sendWin(Player toPlayer) {
        System.out.println("xd");
    }

    //Invia il messaggio di update map
    public void sendMapUpdate() {
        System.out.println("lol");
    }
}