package it.polimi.ingsw.PSP23.server;

import it.polimi.ingsw.PSP23.client.Client;
//import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.model.god.Apollo;
import it.polimi.ingsw.PSP23.model.god.Demeter;
import it.polimi.ingsw.PSP23.view.RemoteView;
import it.polimi.ingsw.PSP23.view.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT=13245;
    private ServerSocket serverSocket;

    private ExecutorService executor= Executors.newFixedThreadPool(5); //probabilmente basta falo con 3 al massimo, da vedere
    private Map<String, ClientConnection> waitingConnection2vs2=new LinkedHashMap<>();
    private Map<String, ClientConnection> waitingConnection3vs3=new LinkedHashMap<>();
    private ArrayList<Player> playing2s=new ArrayList<>();
    private ArrayList<Player> playing3s=new ArrayList<>();
    private ArrayList<ClientConnection>conn2s=new ArrayList<>();
    private ArrayList<ClientConnection>conn3s=new ArrayList<>();

    Controller controller;

    /**
     * This method deregisters a connection
     * @param c the connection we want to remove
     */
    public synchronized void deregisterConnection(ClientConnection c){
        for(int i=0;i<playing2s.size();i++){
            if(playing2s.get(i).getIpAddress().equals(c.getIpAddress())){
                playing2s.remove(playing2s.get(i));
                conn2s.remove(conn2s.get(i));
                c.closeConnection();
            }
        }
        for(int i=0;i<playing3s.size();i++){
            if(playing3s.get(i).getIpAddress().equals(c.getIpAddress())){
                playing3s.remove(playing3s.get(i));
                conn3s.remove(conn3s.get(i));
                c.closeConnection();
            }
        }
        if(conn2s.size()==0 && conn3s.size()==0){
            try {
                serverSocket.close();
                executor.shutdownNow();

                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Getter for getConn2s
     * @return getConn2s()
     */
    public ArrayList<ClientConnection> getConn2s() {
        return conn2s;
    }

    /**
     * Getter for getConn3s
     * @return getConn3s
     */
    public ArrayList<ClientConnection> getConn3s() {
        return conn3s;
    }

    /**
     * This method adds a player to a waitingList, based on the numberOfPlayers paramether
     * After the waiting list is full, the method starts a new march
     * @param c the client connection we want to add to the list
     * @param name the name of the player who joins the lobby
     * @param numberOfPlayers the size of the match, it can be 2 or 3
     */
    public synchronized void lobby(ClientConnection c, String name, int numberOfPlayers){
        if(numberOfPlayers==2){
            waitingConnection2vs2.put(name, c);
        }
        else if(numberOfPlayers==3){
            waitingConnection3vs3.put(name, c);
        }
        if(numberOfPlayers==2){
            c.asyncSend("Benvenuto nella lobby a 2 giocatori");

            System.out.println("Si e' connesso "+name);
            if(waitingConnection2vs2.size()==2){
                System.out.println("istanzio il controller per una partita a 2 giocatori");
                Game game=new Game(2);
                controller=new Controller(game);
                Set<Map.Entry<String, ClientConnection>> st= waitingConnection2vs2.entrySet();

                for(Map.Entry<String, ClientConnection>me:st){
                    String nome=me.getKey();
                    String ip=me.getValue().getIpAddress();
                    conn2s.add(me.getValue());
                    System.out.println("Creo il giocatore "+nome+" con ip "+ip);
                    Player p=new Player(nome, ip);
                    playing2s.add(p);
                }
                waitingConnection2vs2.clear();
                //conn.clear();
                playing2s.get(0).setColor(Color.BLUE);
                playing2s.get(0).setPlayerNumber(0);
                playing2s.get(1).setColor(Color.RED);
                playing2s.get(1).setPlayerNumber(1);
                controller.addPlayer(playing2s.get(0));
                controller.addPlayer(playing2s.get(1));


                View player1view=new RemoteView(playing2s.get(0),conn2s.get(0));
                View player2view=new RemoteView(playing2s.get(1),conn2s.get(1));
                game.addObserver(player1view);
                game.addObserver(player2view);
                controller.addPlayerView(player1view);
                controller.addPlayerView(player2view);
                player1view.addObserver(controller);
                player2view.addObserver(controller);

                System.out.println("fino a qua funziona");


                System.out.println(playing2s.get(0).getPlayerNumber());
                System.out.println(playing2s.get(1).getPlayerNumber());


                if(game.isPlayerTurn(playing2s.get(0))){
                    conn2s.get(0).asyncSend("e' il tuo turno");
                    conn2s.get(0).asyncSend("Scegli 2 dei tra quelli disponibili: ");
                    conn2s.get(0).asyncSend(Arrays.toString(God.getAllGods().toArray()));
                    conn2s.get(0).asyncSend("Sintassi del comando: SELECT_GODS:<god1>,<god2>");
                    conn2s.get(1).asyncSend("Attendi il tuo turno");
                }
                else{
                    conn2s.get(0).asyncSend("Attendi il tuo turno");
                    conn2s.get(1).asyncSend("e' il tuo turno");
                }


            }
        }
        else if(numberOfPlayers==3) {

            c.asyncSend("Benvenuto nella lobby a 3 giocatori");
            System.out.println("Si e' connesso " + name);
            if (waitingConnection3vs3.size() == 3) {
                System.out.println("istanzio il controller per una partita a 3 giocatori");
                Game game=new Game(3);
                controller=new Controller(game);
                Set<Map.Entry<String, ClientConnection>> st= waitingConnection3vs3.entrySet();

                for(Map.Entry<String, ClientConnection>me:st){
                    String nome=me.getKey();
                    String ip=me.getValue().getIpAddress();
                    conn3s.add(me.getValue());
                    System.out.println("Creo il giocatore "+nome+" con ip "+ip);
                    Player p=new Player(nome, ip);
                    playing3s.add(p);
                }
                waitingConnection3vs3.clear();
                //conn.clear();
                playing3s.get(0).setColor(Color.BLUE);
                playing3s.get(0).setPlayerNumber(0);
                playing3s.get(1).setColor(Color.RED);
                playing3s.get(1).setPlayerNumber(1);
                playing3s.get(2).setColor(Color.WHITE);
                playing3s.get(2).setPlayerNumber(2);
                controller.addPlayer(playing3s.get(0));
                controller.addPlayer(playing3s.get(1));
                controller.addPlayer(playing3s.get(2));


                View player1view=new RemoteView(playing3s.get(0),conn3s.get(0));
                View player2view=new RemoteView(playing3s.get(1),conn3s.get(1));
                View player3view=new RemoteView(playing3s.get(2),conn3s.get(2));
                game.addObserver(player1view);
                game.addObserver(player2view);
                game.addObserver(player3view);
                controller.addPlayerView(player1view);
                controller.addPlayerView(player2view);
                controller.addPlayerView(player3view);
                player1view.addObserver(controller);
                player2view.addObserver(controller);
                player3view.addObserver(controller);

                System.out.println("fino a qua funziona");


                System.out.println(playing3s.get(0).getPlayerNumber());
                System.out.println(playing3s.get(1).getPlayerNumber());
                System.out.println(playing3s.get(2).getPlayerNumber());



                if(game.isPlayerTurn(playing3s.get(0))){
                    conn3s.get(0).asyncSend("e' il tuo turno");
                    conn3s.get(0).asyncSend("Scegli 3 dei tra quelli disponibili: ");
                    conn3s.get(0).asyncSend(Arrays.toString(God.getAllGods().toArray()));
                    conn3s.get(0).asyncSend("Sintassi del comando: SELECT_GODS:<god1>,<god2>,<god3>");
                    conn3s.get(1).asyncSend("Attendi il tuo turno");
                    conn3s.get(2).asyncSend("Attendi il tuo turno");
                }
                else if(game.isPlayerTurn(playing3s.get(1))){
                    conn3s.get(1).asyncSend("e' il tuo turno");
                    conn3s.get(0).asyncSend("Attendi il tuo turno");
                    conn3s.get(2).asyncSend("Attendi il tuo turno");
                }
                else {
                    conn3s.get(2).asyncSend("e' il tuo turno");
                    conn3s.get(0).asyncSend("Attendi il tuo turno");
                    conn3s.get(1).asyncSend("Attendi il tuo turno");
                }
            }
        }
    }

    /**
     * Constructor
     * @throws IOException NA
     */
    public Server() throws IOException {
        this.serverSocket=new ServerSocket(PORT);
    }

    /**
     * Stats the server and waits for new connections
     */
    public void run(){
        try{
        while(true){

                Socket s=serverSocket.accept();
                SocketClientConnection socketConnection= new SocketClientConnection(s, this);
                executor.submit(socketConnection);

        }
        }catch(IOException e){
            System.out.println("Connection error");
        }
    }

    /**
     * Method used to terminate the game's timer, in order to allow the server to host a new match
     */
    public void setTimerToZero(){
        controller.setTimeToZero();
    }


}
