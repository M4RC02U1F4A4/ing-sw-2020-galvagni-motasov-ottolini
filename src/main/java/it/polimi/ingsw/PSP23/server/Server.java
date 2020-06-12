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
    private ArrayList<Player> players=new ArrayList<>();
    private ArrayList<ClientConnection>conn=new ArrayList<>();

    public synchronized void deregisterConnection(ClientConnection c){
        /*List<String> threes = new ArrayList<>(waitingConnection3vs3.keySet());
        List<String> twos = new ArrayList<>(waitingConnection2vs2.keySet());*/
        if(waitingConnection3vs3.containsValue(c)){
            System.out.println("Disconnetto dalla partita 3v3 il giocatore "+c.getIpAddress());
            Iterator<String> iterator = waitingConnection3vs3.keySet().iterator();
            while(iterator.hasNext()){
                waitingConnection3vs3.get(iterator.next()).closeConnection();
            }
        }

        if(waitingConnection2vs2.containsValue(c)){
            System.out.println("Disconnetto dalla partita 2vs2 il giocatore "+c.getIpAddress());

            Iterator<String> iterator = waitingConnection2vs2.keySet().iterator();
            while(iterator.hasNext()){
                waitingConnection2vs2.get(iterator.next()).closeConnection();
            }
        }
        System.out.println("XD");
    }

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
                Controller controller=new Controller(game);
                Set<Map.Entry<String, ClientConnection>> st= waitingConnection2vs2.entrySet();

                for(Map.Entry<String, ClientConnection>me:st){
                    String nome=me.getKey();
                    String ip=me.getValue().getIpAddress();
                    conn.add(me.getValue());
                    System.out.println("Creo il giocatore "+nome+" con ip "+ip);
                    Player p=new Player(nome, ip);
                    players.add(p);
                }
                players.get(0).setColor(Color.BLUE);
                players.get(0).setPlayerNumber(0);
                players.get(1).setColor(Color.RED);
                players.get(1).setPlayerNumber(1);
                controller.addPlayer(players.get(0));
                controller.addPlayer(players.get(1));


                View player1view=new RemoteView(players.get(0),conn.get(0));
                View player2view=new RemoteView(players.get(1),conn.get(1));
                game.addObserver(player1view);
                game.addObserver(player2view);
                controller.addPlayerView(player1view);
                controller.addPlayerView(player2view);
                player1view.addObserver(controller);
                player2view.addObserver(controller);

                System.out.println("fino a qua funziona");


                System.out.println(players.get(0).getPlayerNumber());
                System.out.println(players.get(1).getPlayerNumber());

                conn.get(0).asyncSend(playersList());
                conn.get(1).asyncSend(playersList());

                if(game.isPlayerTurn(players.get(0))){
                    conn.get(0).asyncSend("e' il tuo turno");
                    conn.get(0).asyncSend("Scegli 2 dei tra quelli disponibili: ");
                    conn.get(0).asyncSend(Arrays.toString(God.getAllGods().toArray()));
                    conn.get(0).asyncSend("Sintassi del comando: SELECT_GODS:<god1>,<god2>");
                    conn.get(1).asyncSend("Attendi il tuo turno");
                }
                else{
                    conn.get(0).asyncSend("Attendi il tuo turno");
                    conn.get(1).asyncSend("e' il tuo turno");
                }


            }
        }
        else if(numberOfPlayers==3) {

            c.asyncSend("Benvenuto nella lobby a 3 giocatori");
            System.out.println("Si e' connesso " + name);
            if (waitingConnection3vs3.size() == 3) {
                System.out.println("istanzio il controller per una partita a 3 giocatori");
                Game game=new Game(3);
                Controller controller=new Controller(game);
                Set<Map.Entry<String, ClientConnection>> st= waitingConnection3vs3.entrySet();

                for(Map.Entry<String, ClientConnection>me:st){
                    String nome=me.getKey();
                    String ip=me.getValue().getIpAddress();
                    conn.add(me.getValue());
                    System.out.println("Creo il giocatore "+nome+" con ip "+ip);
                    Player p=new Player(nome, ip);
                    players.add(p);
                }
                players.get(0).setColor(Color.BLUE);
                players.get(0).setPlayerNumber(0);
                players.get(1).setColor(Color.RED);
                players.get(1).setPlayerNumber(1);
                players.get(2).setColor(Color.WHITE);
                players.get(2).setPlayerNumber(2);
                controller.addPlayer(players.get(0));
                controller.addPlayer(players.get(1));
                controller.addPlayer(players.get(2));


                View player1view=new RemoteView(players.get(0),conn.get(0));
                View player2view=new RemoteView(players.get(1),conn.get(1));
                View player3view=new RemoteView(players.get(2),conn.get(2));
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


                System.out.println(players.get(0).getPlayerNumber());
                System.out.println(players.get(1).getPlayerNumber());
                System.out.println(players.get(2).getPlayerNumber());

                conn.get(0).asyncSend(playersList());
                conn.get(1).asyncSend(playersList());
                conn.get(2).asyncSend(playersList());


                if(game.isPlayerTurn(players.get(0))){
                    conn.get(0).asyncSend("e' il tuo turno");
                    conn.get(0).asyncSend("Scegli 3 dei tra quelli disponibili: ");
                    conn.get(0).asyncSend(Arrays.toString(God.getAllGods().toArray()));
                    conn.get(0).asyncSend("Sintassi del comando: SELECT_GODS:<god1>,<god2>,<god3>");
                    conn.get(1).asyncSend("Attendi il tuo turno");
                    conn.get(2).asyncSend("Attendi il tuo turno");
                }
                else if(game.isPlayerTurn(players.get(1))){
                    conn.get(1).asyncSend("e' il tuo turno");
                    conn.get(0).asyncSend("Attendi il tuo turno");
                    conn.get(2).asyncSend("Attendi il tuo turno");
                }
                else {
                    conn.get(2).asyncSend("e' il tuo turno");
                    conn.get(0).asyncSend("Attendi il tuo turno");
                    conn.get(1).asyncSend("Attendi il tuo turno");
                }
            }
        }


        System.out.println("Giocatori in attesa di una partita per 2");
        Set<Map.Entry<String, ClientConnection>> st = waitingConnection2vs2.entrySet();
        for (Map.Entry<String, ClientConnection> me : st) {
            System.out.print(me.getKey() + ":");
            System.out.println(me.getValue().getIpAddress());
        }

        System.out.println("Giocatori in attesa di una partita per 3");
        Set<Map.Entry<String, ClientConnection>> sti = waitingConnection3vs3.entrySet();
        for (Map.Entry<String, ClientConnection> me : sti) {
            System.out.print(me.getKey() + ":");
            System.out.println(me.getValue().getIpAddress());
        }


    }

    public Server() throws IOException {
        this.serverSocket=new ServerSocket(PORT);
    }

    public void run(){
        while(true){
            try{
                Socket s=serverSocket.accept();
                SocketClientConnection socketConnection= new SocketClientConnection(s, this);
                executor.submit(socketConnection);
            }catch(IOException e){
                System.out.println("Connection error");
            }
        }
    }

    public String playersList(){
        String msg="";
        for(int i=0;i<players.size();i++){
            msg=msg+"PLAYER"+(i+1)+":"+players.get(i).getName()+"-"+players.get(i).getColor()+"\n";
        }
        return msg;
    }

}
