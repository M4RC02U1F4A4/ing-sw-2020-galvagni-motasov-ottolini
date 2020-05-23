package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.*;

import java.util.ArrayList;

public class Game {
    private Map map;
    private Player[] players;
    private String[] availableGods;
    private TurnManager turnManager;
    private boolean activeWorker, ChronoIsHere;
    private int numPlayers, colorVariable;

    public Game(int numPlayer) {
        map=new Map();
        numPlayers = numPlayer;
        players = new Player[numPlayer];
        availableGods = new String[numPlayer];
        turnManager = new TurnManager();
        turnManager.setPlayerNumber(numPlayer);
        ChronoIsHere = false;
    }

    //Set functions, game preparation

    private void godChoose(String god1, String god2, String god3) {
        availableGods[0] = god1;
        availableGods[1] = god2;
        availableGods[2] = god3;
        this.nextGameSetUpPhase();
    }

    //TODO finire di inserire tutti i god
    private void setGod(String god) {
        if (god.equals(availableGods[0]))
            availableGods[0] = "Scelto";
        else if (god.equals(availableGods[1]))
            availableGods[1] = "Scelto";
        else if (god.equals(availableGods[2]))
            availableGods[2] = "Scelto";
        switch (god) {
            case "Apollo":
                getCurrentPlayer().setGod(new Apollo());
                break;
            case "Artemis":
                getCurrentPlayer().setGod(new Artemis());
                break;
            case "Athena":
                getCurrentPlayer().setGod(new Athena());
                for (int cont = 0; cont < numPlayers; cont++)
                    players[cont].getGod().AthenaIsHere();
                break;
            case "Chronus":
                getCurrentPlayer().setGod(new Chronus());
                this.ChronoIsHere = true;
                break;
            case "Hera":
                getCurrentPlayer().setGod(new Hera());
                for (int cont = 0; cont < numPlayers; cont++)
                    players[cont].getGod().HeraIsHere();
                break;
        }
        this.nextGameSetUpPhase();
    }

    private void setWorker(int x, int y) {
        getCurrentPlayer().placeWorker(map.getCell(x, y));
        nextGameSetUpPhase();
    }

    private void nextGameSetUpPhase() {
        turnManager.nextPhaseSetUp();
        if (Phase.END == turnManager.getCurrentPhase())
            turnManager.setCurrentPlayer(getCurrentPlayer());
    }

    //Set functions, game

    public void chooseActiveWorker(Worker w) {
        activeWorker = getCurrentPlayer().getWorkerByNumber(0) == w;
        nextGamePhase();
    }

    // TODO chronus completed tower
    private void build(int x, int y, Status b) {
        getCurrentPlayer().getGod().build(map.getCell(x, y), b, getActiveWorker());
        nextGamePhase();
    }

    private void move(int x, int y) {
        getCurrentPlayer().getGod().move(map.getCell(x, y), getActiveWorker(), map);
        nextGamePhase();
    }

    private void skipAction() {
        switch (getCurrentPlayer().getGod().getName()) {
            case "Artemis":
                break;
            case "Demeter":
                break;
            case "Hephaestus":
                break;
            case "Hestia":
                break;
            case "Prometheus":
                break;
            case "Triton":
                break;
        }
        nextGamePhase();
    }

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
                if (getCurrentPlayer().getGod().checkWin(this.getActiveWorker()))
                    sendWin(getCurrentPlayer());
                nextGamePhase();
                break;
            case CHECK_LOSE_MOVE:
                if (getCurrentPlayer().getGod().checkLossMove(this.getActiveWorker(), map))
                    sendLoss(getCurrentPlayer());
                nextGamePhase();
                break;
            case CHECK_LOSE_BUILD:
                if (getCurrentPlayer().getGod().checkLossBuild(this.getActiveWorker(), map))
                    sendLoss(getCurrentPlayer());
                nextGamePhase();
                break;
            case END:
                break;
        }
    }

    // internal functions

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
        if (activeWorker)
            return getCurrentPlayer().getWorkerByNumber(0);
        else
            return getCurrentPlayer().getWorkerByNumber(1);
    }

    private Player getCurrentPlayer() {
        return players[this.getCurrentPlayerNum()];
    }

    // In and Out functions

    public int addPlayer(String name, String ip) {
        if (colorVariable >= numPlayers)
            return -1;
        players[colorVariable] = new Player(name, ip);
        players[colorVariable].setColor(getColor());
        colorVariable++;
        return 0;
    }

    public Phase getPhase() {
        return turnManager.getCurrentPhase();
    }

    //TODO check this function
    public int getCurrentPlayerNum() {
        return turnManager.getCurrentPlayerNumber() - 1;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<String> getAllGodList() {
        return God.getAllGods();
    }

    // TODO return the list of gods avaiable
    public String getGodList() {
        return "chiapasu";
    }

    public void performeMove(String Move) {
    }


    /*
    TODO IVAN tutte queste funzioni sono tue, l'ideale sarebbe lasciare la classe game come istanza del gioco e chiamare
    le funzioni di comunicazione con i player attraverso un'altra classe.
     */

    //Invia il messaggio di partita persa
    public void sendLoss(Player toPlayer) {

    }

    //Invia il messaggio di partita vinta
    public void sendWin(Player toPlayer) {

    }

    //Invia il messaggio di update map
    public void sendMapUpdate() {}


/*
    public Map getMap() {
        return map;
    }

    /**
     * add a Player to the current game, then a god is randomly assigned to that player and then a color.
     * The god is chosen between the unused ones, the used ones are saved in the <code>takenGods</code> ArrayList
     * @param p the player we want to add to the game
     *
    public void addPlayer(Player p){
        //p.setColor(getUnusedColor());
        p.setPlayerNumber(players.size());
        players.add(p);
        turnManager.addPlayer();
    }

    public int getTurnNumber(){
        return turnManager.getCurrentPlayerNumber();
    }

    public void nextPhase(){
        turnManager.nextPhaseGame();
    }

    // TODO not like this!
    public void nextTurn() {
        //turnManager.nextTurn();
        // instead something like
        if (Phase.END == turnManager.getCurrentPhase()) {
            turnManager.setCurrentPlayer(players.get(turnManager.getCurrentPlayerNumber()));
            // game set up
        }
        else if (Phase.CHOOSE_WORKER == turnManager.getCurrentPhase()) {
            turnManager.setCurrentPlayer(players.get(turnManager.getCurrentPlayerNumber()));
        }
        else
            return;
            // error
    }

    public int getNumberOfPlayers(){
        return players.size();
    }

    public void performeMove(int x, int y, Player player, Action action, int nworker){
        if(action == Action.BUILD){
            //TODO build
            player.getGod().build(map.getCell(1,0),Status.BUILT,player.getWorkerByNumber(nworker));
        }
        else if(action == Action.MOVE){
            //TODO move
            player.getGod().startTurn(false);
            player.getGod().move(map.getCell(1,0),player.getWorkerByNumber(nworker),map);
        }
        else if(action==Action.PLACE_WORKERS){
            //TODO PLACE WORKERS
        }
        else if(action==Action.CHOOSE_GODS){
            //TODO CHOOSE GODS
        }
        else return;//magari anche qui con un'eccezione
    }

    /**
     * Method used to give each player a different color
     * @return
     *
    public Color getUnusedColor(){
        switch (colourVariable) {
            case (0):
                colourVariable++;
                return Color.RED;
            case (1):
                colourVariable++;
                return Color.BLUE;
            case (2):
                colourVariable++;
                return Color.WHITE;
            default:return null;
        }
    }

    public Player getPlayer(int pos){
        return players.get(pos);
    }

    public ArrayList<String> getAvailableGods(){
        return availableGods;
    }

    public int getCurrentPlayer(){
        return turnManager.getCurrentPlayerNumber();
    }

    public Phase getCurrentPhase(){
        return turnManager.getCurrentPhase();
    }

    public boolean isPlayerTurn(Player p){
        if(p.getPlayerNumber()==getCurrentPlayer())
            return true;
        else
            return false;
    }

    public void addGod(Player p, String god){
        switch (god){
            case "Apollo":
                p.setGod(new Apollo());
                break;
            case "Artemis":
                p.setGod(new Artemis());
                break;
            case "Athena":
                p.setGod(new Athena());
                break;
            case "Atlas":
                p.setGod(new Atlas());
                break;
            case "Chronus":
                p.setGod(new Chronus());
                break;
            case "Demeter"
                    :p.setGod(new Demeter());
                break;
            case "Hephaestus":
                p.setGod(new Hephaestus());
                break;
            case "Hera":
                p.setGod(new Hera());
                break;
            case "Hestia":
                p.setGod(new Hestia());
                break;
            case "Minotaur":
                p.setGod( new Minotaur());
                break;
            case "Pan":
                p.setGod(new Pan());
                break;
            case "Prometheus":
                p.setGod(new Prometheus());
                break;
            case "Triton":
                p.setGod(new Triton());
                break;
            case "Zeus"
                    :p.setGod(new Zeus());
                break;
        }

    }
    */
}
