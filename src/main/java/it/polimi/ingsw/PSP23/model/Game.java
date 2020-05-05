package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.*;
import it.polimi.ingsw.PSP23.observer.Observable;

import java.util.ArrayList;

public class Game extends Observable<Message> {
    private Map map=new Map();
    private ArrayList<Player> players=new ArrayList<>();
    private ArrayList<String> takenGods=new ArrayList<>();
    private TurnManager turnManager=new TurnManager(0);
    private int colourVariable=0;

    //TODO creare un metodo che controlli che il dio non sia gia' stato usato, c tale scopo usare takenGods

    public Map getMap(){ return map; }

    /**
     * add a Player to the current game, then a god is randomly assigned to that player and then a color.
     * The god is chosen between the unused ones, the used ones are saved in the <code>takenGods</code> ArrayList
     * @param p the player we want to add to the game
     */
    public void addPlayer(Player p){

        God g=new God();
        String tmp=g.choseRandomGod();
        while(takenGods.contains(tmp)){
            tmp=g.choseRandomGod();
        }
        takenGods.add(tmp);
        //String gods[]={"Apollo","Artemis",",Athena","Atlas","Chronus","Demeter","Hephaestus","Hera","Hestia","Minotaur","Pan","Prometheus","Triton","Zeus"};
        switch (tmp){
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
        p.setColor(getUnusedColor());
        players.add(p);
        turnManager.setPlayerNumber(players.size());

    }

    public int getTurnNumber(){
        return turnManager.getCurrentPlayer();
    }

    public void nextPhase(){
        turnManager.nextPhase();
    }

    public void nextTurn(){
        turnManager.nextPlayer();
    }

    public int getNumberOfPlayers(){
        return players.size();
    }



    public void performeMove(int x, int y, Player player, Action action){
        if(action == Action.BUILD){
            //TODO build
            //TOTO build
        }
        else if(action == Action.MOVE){
            //TODO move
            //takenGods.add(players.get(0).getGod().choseRandomGod());
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

    //TODO: COSE MANCANTI:
    //TODO:VERIFICARE SE SI HA PERSO SEMPRE TRAMITE UN METODO



}
