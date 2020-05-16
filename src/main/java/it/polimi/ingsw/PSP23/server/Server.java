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
    private Map<String, ClientConnection> waitingConnection2vs2=new HashMap<>();
    private Map<String, ClientConnection> waitingConnection3vs3=new HashMap<>();
    private ArrayList<Player> players=new ArrayList<>();
    private ArrayList<ClientConnection>conn=new ArrayList<>();
    public synchronized void deregisterConnection(ClientConnection c){
        System.out.println("XD");
    }

    public synchronized void lobby(ClientConnection c, String name, int numberOfPlayers){
        if(numberOfPlayers==2){
            waitingConnection2vs2.put(name, c);
            c.asyncSend("Benvenuto nella lobby a 2 giocatori");
            System.out.println("Si e' connesso "+name);
            if(waitingConnection2vs2.size()==2){
                System.out.println("istanzio il controller");
                Game game=new Game();
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

                Worker[] workers = new Worker[2];
                workers[0]=new Worker(game.getMap().getCell(0,0),Color.BLUE);
                workers[1]=new Worker(game.getMap().getCell(0,1),Color.BLUE);
                players.get(0).setWorkers(workers);

                //controller.addPlayer(players.get(0));

                workers[0]=new Worker(game.getMap().getCell(3,3),Color.RED);
                workers[1]=new Worker(game.getMap().getCell(3,4),Color.RED);
                players.get(1).setWorkers(workers);
                players.get(1).setColor(Color.RED);
                players.get(1).setGod(new Demeter());
                //controller.addPlayer(players.get(1));


                View player1view=new RemoteView(players.get(0),conn.get(0));
                View player2view=new RemoteView(players.get(1),conn.get(1));
                game.addObserver(player1view);
                game.addObserver(player2view);
                controller.addPlayerView(player1view);
                controller.addPlayerView(player2view);




                conn.get(0).asyncSend(game.getMap());
                conn.get(1).asyncSend(game.getMap());

                if(game.isPlayerTurn(players.get(0))){
                    conn.get(0).asyncSend("e' il tuo turno");
                    conn.get(1).asyncSend("non e' il tuo turno");
                }
                else{
                    conn.get(0).asyncSend("non e' il tuo turno");
                    conn.get(1).asyncSend("e' il tuo turno");
                }


            }
        }
        else if(numberOfPlayers==3) {
            waitingConnection3vs3.put(name, c);
            c.asyncSend("Benvenuto nella lobby a 3 giocatori");
            System.out.println("Si e' connesso " + name);
            if (waitingConnection3vs3.size() == 3) {
                System.out.println("istanzio il controller");
                Controller controller = new Controller(new Game());
                Set<Map.Entry<String, ClientConnection>> st = waitingConnection3vs3.entrySet();
                for (Map.Entry<String, ClientConnection> me : st) {
                    String nome = me.getKey();
                    String ip = me.getValue().getIpAddress();
                    System.out.println("Creo il giocatore " + nome + " con ip " + ip);
                    //controller.addPlayer(new Player(nome, ip));
                }
                System.out.println("HO AGGIUNTO TUTTI E TRE I GIOCATORI AAAAAAAAAAAAAAAAAAAA");
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
}
