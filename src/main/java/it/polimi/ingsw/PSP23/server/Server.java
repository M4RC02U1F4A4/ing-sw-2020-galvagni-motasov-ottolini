package it.polimi.ingsw.PSP23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT=13245;
    private ServerSocket serverSocket;

    private ExecutorService executor= Executors.newFixedThreadPool(5); //probabilmente basta falo con 3 al massimo, da vedere

    public synchronized void deregisterConnection(ClientConnection c){
        System.out.println("XD");
    }

    public synchronized void lobby(ClientConnection c, String name){

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
