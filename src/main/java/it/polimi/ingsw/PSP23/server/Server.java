package it.polimi.ingsw.PSP23.server;

import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public synchronized void deregisterConnection(ClientConnection c){
        System.out.println("XD");
    }

    public synchronized void lobby(ClientConnection c, String name, int numberOfPlayers){
        if(numberOfPlayers==2){
            waitingConnection2vs2.put(name, c);
            c.asyncSend("Benvenuto nella lobby a 2 giocatori");
            System.out.println("Si e' connesso "+name);
            if(waitingConnection2vs2.size()==2){
                Controller controller=new Controller(new Game());
                //TODO estrarre i nomi da qui



                //controller.addPlayer(new Player(waitingConnection2vs2.get(0),waitingConnection2vs2.get(1).toString())
            }
        }
        else if(numberOfPlayers==3){
            waitingConnection3vs3.put(name, c);
            c.asyncSend("Benvenuto nella lobby a 3 giocatori");
            System.out.println("Si e' connesso "+name);
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
