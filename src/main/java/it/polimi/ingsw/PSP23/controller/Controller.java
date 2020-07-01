package it.polimi.ingsw.PSP23.controller;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Observer<PlayerMove>{
    private final Game game;
    private final static int TIME_LIMIT=30;

    private ArrayList<View> players = new ArrayList<>();
    private Action actionBeingPerformed;
    private Status whatToBuild = Status.FREE;
    private ArrayList<String> arguments = new ArrayList<>();
    private int x = -1, y = -1;
    private int chosenWorker = -1;
    private int removedPlayer=-1,currPl=0;

    private final Timer timer = new Timer();
    private int timeRunningOut=TIME_LIMIT*5;

    /**
     * Adds a player to the match
     * @param p the player to add
     */
    public void addPlayer(Player p){
        game.addPlayer(p.getName(),p.getIpAddress());
    }

    /**
     * Adds a View to the View list
     * @param v the view to add
     */
    public void addPlayerView(View v){
        players.add(v);
    }

    /**
     * Constructor
     * @param game the game we are playing on
     */
    public Controller(Game game) {
        super();

        this.game = game;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRunningOut--;
                System.out.println(timeRunningOut);
                if(timeRunningOut<=0){
                    System.out.println("TEMPO SCADUTO!");
                    timer.cancel();
                    sendToEverybody("Partita terminata: timeout");
                    closeEverybody();

                }
            }
        },0,1000);

    }

    /**
     *This method actually makes the move: it uses the local variables and the set of messages contained in the PlayerMove move in order to make any choice in the game
     * It checks if the player can send a message or it's not his turn.
     * After that check, depending on the gamePhase, the method performes the move and notifies the next player on what he needs to do
     * This method also checks if the current player wins or loses and eventually he notifies them about it
     * @param move the player move
     */
    public synchronized void  performMove(PlayerMove move){
        //players.get(currPl).showMessage(currPl+"-"+game.getCurrentPlayerNum());
        if (game.isPlayerTurn(players.get(currPl).getPlayer())) {
            switch (move.getCommand()) {
                case "SELECT_GODS": {
                    if((players.size()==2 && God.exists(arguments.get(0))==1&&God.exists(arguments.get(0))==1) ||(players.size()==3 && God.exists(arguments.get(0))==1&&God.exists(arguments.get(0))==1 && God.exists(arguments.get(2))==1)){
                        game.godChoose(arguments.get(0), arguments.get(1), arguments.get(2));
                        sendToEverybody("GODSC:" +game.godsc());
                        sendToNextPlayer("Scegli un dio tra quelli disponibili:" + game.getGodList());
                        sendToNextPlayer("Sintassi del comando: \nCHOOSE_GOD:<god>");
                        sendToRemainingPlayers("Attendi il tuo turno");
                        break;
                    }else {
                        players.get(currPl).showMessage("Comando non valido! Riprova");
                        break;
                    }
                }
                case "CHOOSE_GOD": {
                    if(game.setGod(arguments.get(0))==-1){
                        players.get(currPl).showMessage("Parametro divinita' non valido: riprova");
                        break;
                    }
                    if(game.getPhase()==Phase.GOD_PICK){
                        sendToNextPlayer("Scegli un dio tra quelli disponibili:"+game.getGodList());
                        sendToNextPlayer("Sintassi del comando: \nCHOOSE_GOD:<god>");
                        sendToRemainingPlayers("Attendi il tuo turno");
                    }
                    else{
                        sendToEverybody(playersList());
                        sendToEverybody("STARTING THE GAME");
                        sendToNextPlayer("Piazza un worker sulla mappa \nSintassi del comando:\nPLACE_WORKER:<x>,<y>");
                    }
                    break;
                }
                case "PLACE_WORKER":
                case "CHOOSE_WORKER":
                case "BUILD":
                case "MOVE":
                case "SKIP": {
                    if ((chosenWorker == 0 || chosenWorker == 1 || chosenWorker == -1) && (x >= -1 && x < 5) && (y >= -1 && y < 5)) {
                        if (0 <= game.performeMove(actionBeingPerformed, whatToBuild, chosenWorker, x, y)) {
                            sendUpdatedMap();
                            sendToEverybody("TURN:"+players.get(game.getCurrentPlayerNum()).getPlayer().getName());
                            switch (game.getPhase()) {
                                case WORKER_HOUSING:
                                    sendToNextPlayer("Piazza un worker sulla mappa \nSintassi del comando:\nPLACE_WORKER:<x>,<y>");
                                    break;
                                case CHOOSE_WORKER:
                                    sendToNextPlayer("Scegli il worker per questo turno:\nSintassi del comando:\nCHOOSE_WORKER:<nWorker>");
                                    break;
                                case MOVE:
                                    sendToNextPlayer("Scegli dove muoverti:\nSintassi del comando:\nMOVE:<x>,<y>");
                                    break;
                                case BUILD:
                                    sendToNextPlayer("Scegli dove muoverti:\nSintassi del comando:\nBUILD:<x>,<y>,<blocco o cupola>");
                                    break;
                                case GOOD_NEWS:
                                    sendToNextPlayer("WIN");
                                    sendToRemainingPlayers("LOSE");
                                    closeEverybody();
                                    break;
                                case BAD_NEWS:
                                    move.getView().showMessage("LOSE");
                                    if (3 == players.size()) {
                                        players.get(move.getPlayer().getPlayerNumber()).isOver();
                                        removePlayer();
                                        //players.get(move.getPlayer().getPlayerNumber()).close();
                                        //closeNextPlayer();

                                    }
                                    else {
                                        sendToRemainingPlayers("WIN");
                                        closeEverybody();
                                    }
                                    break;
                            }
                            sendToRemainingPlayers("Attendi il tuo turno");
                        } else {
                            players.get(currPl).showMessage("Comando non valido: riprova");
                        }
                    } else
                        players.get(currPl).showMessage("Comando non valido: riprova");
                    break;
                }
            }
        }
        else {
            move.getView().showMessage("NON È IL TUO TURNO!");
        }
    }

    /**
     * Method used to disconnect a player whenever he loses.
     * When we disconnect a player, we need to adjust the number of remaining players, in order to match it with the TurnManager
     * After adjusting, we send everybody the updated map and we wait for the next player to performe his moves
     */
    private void removePlayer() {
        System.out.println("Il giocatore"+game.getCurrentPlayerNum()+" ha perso");
        removedPlayer=game.getCurrentPlayerNum();
        players.remove(players.get(game.getCurrentPlayerNum()));
        System.out.println("REMOVING PLAYER: "+game.removePlayer());
        players.get(0).getPlayer().setPlayerNumber(0);
        players.get(1).getPlayer().setPlayerNumber(1);
        sendUpdatedMap();
        System.out.println(playersList());
        sendToEverybody("TURN:"+players.get(game.getCurrentPlayerNum()).getPlayer().getName());
        sendToNextPlayer("Scegli il worker per questo turno:\nSintassi del comando:\nCHOOSE_WORKER:<nWorker>");
    }

    /**
     * Method that calls setActionFromClient(), in order to set local variables for each move, and performes the move
     * @param message the whole comand recieved from the client
     */
    @Override
    public void update(PlayerMove message) {
        setActionFromTheClient(message.getCommand(), message.getArgs(),message.getPlayer());
        performMove(message);
        //inizializzazione a fine turno
        arguments.clear();
        x = -1;
        y = -1;
        chosenWorker = -1;
        whatToBuild = Status.FREE;
    }

    /**
     * Method used everytime a player makes any move. Used to set local variables in order to perform the moves
     * @param msg the comand sent from the player
     * @param args the parameters that are required to perform the move
     * @param him the player, used in order to adjust the number of players whenever a player loses the match
     */
    public void setActionFromTheClient(String msg, String args, Player him){
        resetTimer();
        System.out.println("NUMERO:"+him.getPlayerNumber());
        switch (removedPlayer){
            case 0:{
                if (him.getPlayerNumber()==1){
                    currPl=0;
                }
                else if(him.getPlayerNumber()==2){
                    currPl=1;
                }
                break;
            }
            case 1:{
                if(him.getPlayerNumber()==0){
                    currPl=0;
                }
                else if(him.getPlayerNumber()==2){
                    currPl=1;
                }
                break;
            }
            case 2:
            case -1:{
                currPl=him.getPlayerNumber();
                break;
            }
        }
        removedPlayer=-1;
        String[] tmp=args.split(",");
        switch (msg){
            case "SELECT_GODS":{
                arguments.add(tmp[0]);  //Dio n.1
                arguments.add(tmp[1]);  //Dio n.2
                if(tmp.length==3){
                    arguments.add(tmp[2]);//Eventuale dio n.3
                }
                else{
                    arguments.add("ONLY2");
                }
                actionBeingPerformed=Action.SELECT_GODS;
                break;
            }
            case "CHOOSE_GOD":{
                actionBeingPerformed=Action.CHOOSE_GOD;
                arguments.add(tmp[0]);  //dio scelto per il giocatore
                break;
            }
            case "PLACE_WORKER":{
                actionBeingPerformed=Action.PLACE_WORKER;
                //arguments.add(tmp[0]);//Numero worker
                //chosenWorker=Integer.parseInt(arguments.get(0)); // TODO remove me
                arguments.add(tmp[0]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(0));
                arguments.add(tmp[1]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(1));
                break;
            }
            case "CHOOSE_WORKER":{
                actionBeingPerformed=Action.SELECT_WORKER;
                arguments.add(tmp[0]);//n. worker scelto
                chosenWorker=Integer.parseInt(arguments.get(0));
                break;
            }
            case "MOVE":{
                actionBeingPerformed=Action.MOVE;
                arguments.add(tmp[0]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(0));
                arguments.add(tmp[1]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(1));
                break;
            }
            case "BUILD":{
                actionBeingPerformed=Action.BUILD;
                arguments.add(tmp[0]);//Coordinata x
                this.x=Integer.parseInt(arguments.get(0));
                arguments.add(tmp[1]);//Coordinata y
                this.y=Integer.parseInt(arguments.get(1));
                if(tmp[2].equals("CUPOLA")){
                    whatToBuild=Status.CUPOLA;
                }
                else
                    whatToBuild=Status.BUILT;
                break;
            }
            case "SKIP": {
                actionBeingPerformed = Action.SKIP;
                break;
            }
        }
    }

    /**
     * Method used to send a message to the next playing player
     * @param msg the message we want to send
     */
    public void sendToNextPlayer(String msg){
        int nextPlayer=game.getCurrentPlayerNum();
        /*if (nextPlayer==players.size()){
            nextPlayer=0;
        }*/
        players.get(nextPlayer).showMessage(msg);
    }

    /**
     * Method used to send a message to all players except the next playing player
     * @param msg the message we want to send
     */
    public void sendToRemainingPlayers(String msg){
        for(int i=0;i<players.size();i++){
            if(i!=game.getCurrentPlayerNum()){
                players.get(i).showMessage(msg);
            }
        }
    }

    /**
     * Method used to send everybody a certain message
     * @param msg the message we want to send
     */
    public void sendToEverybody(String msg){
        for(int i=0;i<players.size();i++){
            players.get(i).showMessage(msg);
        }
    }

    /**
     * Method used to send the map to each player
     */
    public void sendUpdatedMap(){
        for (int i=0;i<players.size();i++){
            players.get(i).showMessage(game.getMap());
        }
    }

    /**
     * Method used to send a list of the players.
     * for each player the method returns its name, its color and its god
     * @return the players list
     */
    public String playersList(){
        String msg="";
        for(int i=0;i<players.size();i++){
            //msg=msg+"PLAYER"+(i+1)+":"+players.get(i).getPlayer().getName()+"-"+players.get(i).getPlayer().getColor()+"-"+deiInOrdine.get(i)+"\n";
            msg=msg+"PLAYER"+(i+1)+":"+players.get(i).getPlayer().getName()+"-"+game.getPlayer(i).getColor()+"-"+game.getPlayer(i).getGod().getName()+"\n";
        }
        return msg;
    }

    /**
     * Method used to reset the timer whenever we recieve a message
     */
    public void resetTimer(){
        timeRunningOut=TIME_LIMIT;
    }

    /**
     * Method used to disconnect the next player whenever he loses a match
     */
    public void closeNextPlayer(){

        players.get(game.getCurrentPlayerNum()).isOver();
        players.get(game.getCurrentPlayerNum()).close();
    }

    /*
    public void closeOtherPlayers(){
        for(int i=0;i<players.size();i++){
            if(i!=game.getCurrentPlayerNum()){
                players.get(i).close();
                players.get(i).isOver();
            }
        }
    }*/

    /**
     * Method used to disconnect every player whenever the timer reaches 0
     */
    public void closeEverybody(){
        for(int i=0;i<players.size();i++){
            players.get(i).close();
            players.get(i).isOver();
            timeRunningOut=0;
        }
    }

    /**
     * Method used to set the timer to zero. Used when all clients need to be disconnected due to a connection error.
     * Setting the time to zero allows the server to host a new game
     */
    public void setTimeToZero(){
        timeRunningOut=0;
    }



}
