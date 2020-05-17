package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.*;
import it.polimi.ingsw.PSP23.observer.Observable;


import java.util.ArrayList;

public class Game extends Observable<Message> {
    private Map map=new Map();
    private ArrayList<Player> players=new ArrayList<>();
    private ArrayList<String> availableGods=God.getAllGods();
    private TurnManager turnManager=new TurnManager();
    private int colourVariable=0;


    public Map getMap(){ return map; }



    /**
     * add a Player to the current game, then a god is randomly assigned to that player and then a color.
     * The god is chosen between the unused ones, the used ones are saved in the <code>takenGods</code> ArrayList
     * @param p the player we want to add to the game
     */
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

    public void nextTurn() {turnManager.nextTurn();}

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
     */
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


}
