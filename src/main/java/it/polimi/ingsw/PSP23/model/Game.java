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

    // TODO inside
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
                // TODO così può dare null point exception, trovare altro modo per inizializzare athena e hera
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

    /**
     * set the worker up
     * @param x the cell x
     * @param y the cell y
     * @return 0 if ok
     *        -1 if cell is occupied
     */
    private int setWorker(int x, int y) {
        if (map.getCell(x, y).isOccupied())
            return -1;
        getCurrentPlayer().placeWorker(map.getCell(x, y));
        nextGameSetUpPhase();
        return 0;
    }

    /**
     * to move the turn manager at the start of the game
     */
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

    /**
     * set the active worker
     * @param w 0 for the worker 0 and 1 for the worker 1
     * @return w
     */
    private int setActiveWorker(int w) {
        if (0 == w || 1 == w) {
            activeWorker = w;
            nextGamePhase();
            return w;
        }
        return -1;
    }

    /**
     * do the build
     * @param x of the cell target
     * @param y of the cell target
     * @param b type of block build
     * @return see god.build for an accurate return
     */
    private int build(int x, int y, Status b) {
        int i = getCurrentGod().build(map.getCell(x, y), b, getActiveWorker());
        if (0 <= i) {
            if (ChronusIsHere && 4 == i)
                completedTowers++;
            nextGamePhase();
        }
        return i;
    }

    /**
     * do the move
     * @param x of the cell target
     * @param y of the cell target
     * @return see god.move for an accurate return
     */
    private int move(int x, int y) {
        int i = getCurrentGod().move(map.getCell(x, y), getActiveWorker(), map);
        if (0 <= i)
            nextGamePhase();
        return i;
    }

    // TODO check if it's ok to skip
    /**
     * let some god to skip the move
     * @return 1 if skipped
     *        -1 if not skipped
     */
    private int skipAction() {
        switch (getCurrentGod().getName()) {
            case "Artemis":         //move
            case "Demeter":         //build
            case "Hephaestus":      //build
            case "Hestia":          //build
            case "Prometheus":      //build
            case "Triton":          //move
                if (turnManager.getSkip()) {
                    getCurrentGod().setSkip();
                    nextGamePhase();
                    return 1;
                }
                break;
        }
        return -1;
    }

    // TODO check this
    /**
     * to call only when in a 3 player game and 1 is out
     * @param playerNum the player to remove
     */
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

    // TODO inside
    /**
     * sometimes i'm afraid of my own genius.
     * also this move the turn manager only when needed
     */
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
                nextGamePhase();
                turnManager.setCurrentPlayer(getCurrentPlayer());
                break;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // internal functions
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * getter
     * @return the next free color
     */
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

    /**
     * getter
     * @return the active worker of the current player
     */
    private Worker getActiveWorker() {
        if (0 == activeWorker)
            return getCurrentPlayer().getWorkerByNumber(0);
        else
            return getCurrentPlayer().getWorkerByNumber(1);
    }

    /**
     * getter
     * @return the current player
     */
    private Player getCurrentPlayer() {
        return players[getCurrentPlayerNum()];
    }

    /**
     * getter
     * @return the god of the current player
     */
    private God getCurrentGod() {
        return players[getCurrentPlayerNum()].getGod();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // In and Out functions
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * getter
     * @return the current phase
     */
    public Phase getPhase() {
        return turnManager.getCurrentPhase();
    }

    /**
     * getter
     * @return the current player number
     */
    public int getCurrentPlayerNum() {
        return turnManager.getCurrentPlayerNumber();
    }

    /**
     * check if is the turn of the player p
     * @param p the player to check
     * @return true if it is the turn player, false otherwise
     */
    public boolean isPlayerTurn(Player p){
        if(p.getPlayerNumber() == getCurrentPlayerNum())
            return true;
        else
            return false;
    }

    /**
     * getter
     * @return the map of the game
     */
    public Map getMap() {
        return map;
    }

    public ArrayList<String> getAllGodList() {
        return God.getAllGods();
    }

    /**
     * getter
     * @return string of avaiable gods
     */
    public String getGodList() {
        String tmp = "|";
        for(int i=0; i < numPlayers; i++) {
            if(!availableGods[i].equals("Scelto"))
                tmp = tmp + availableGods[i] + "|";
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
     * @return  num_pos if everything is ok
     *          num_neg if there is an error
     */
    public int performeMove(Action action, Status block, int active, int x, int y) {
        switch (action) {
            case PLACE_WORKER:
                return setWorker(x, y);
            case SELECT_WORKER:
                 return setActiveWorker(active);
            case MOVE:
                return move(x, y);
            case BUILD:
                return build(x, y, block);
            case SKIP:
                return skipAction();
        }
        return -1;
    }

    //TODO IVAN tutte queste funzioni sono tue

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