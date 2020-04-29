package it.polimi.ingsw.PSP23.server;

import it.polimi.ingsw.PSP23.observer.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketClientConnection extends Observable implements ClientConnection, Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private Server server;
    public boolean active = true;

    public SocketClientConnection(Socket socket, Server server){
        this.server=server;
        this.socket=socket;
    }

    public synchronized boolean isActive(){
        return active;
    }

    public String getIpAddress(){
        return socket.getRemoteSocketAddress().toString();
    }

    private synchronized void send(Object message){
        try{
            out.reset();
            out.writeObject(message);
            out.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public synchronized void closeConnection(){
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error while closing the connection");
        }
        active=false;
    }

    private void close(){
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    public void asyncSend(final Object message){
        new Thread((new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        })).start();
    }

    public void run(){
        Scanner in;
        String name;
        try{
            in=new Scanner(socket.getInputStream());
            out=new ObjectOutputStream(socket.getOutputStream());
            send("Inserisci il tuo nome");
            String read=in.nextLine();
            name=read;
            in.reset();
            send("Inserisci il numero di giocatori");
            int nPlayers=in.nextInt();
            //int n=Integer.parseInt(nPlayers);
            server.lobby(this, name,nPlayers);
            while (isActive()){
                read=in.next();
                notify(read);
            }


        }catch (IOException| NoSuchElementException e){
            System.err.println("Errore "+e.getMessage());
        }/*finally {
            close();
        }*/

    }
}
