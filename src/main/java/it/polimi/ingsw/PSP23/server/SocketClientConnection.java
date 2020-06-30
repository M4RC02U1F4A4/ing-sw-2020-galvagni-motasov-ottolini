package it.polimi.ingsw.PSP23.server;

import it.polimi.ingsw.PSP23.observer.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SocketClientConnection extends Observable<String> implements ClientConnection, Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private Server server;
    public boolean active = true;
    private boolean isOver=false;

    /**
     * Sets isOver to true.
     * If true, it means that the client was disconnected from the server, otherwise we were disconnected due to a TimeOut
     */
    public void isOver() {
        isOver=true;
    }

    /**
     * Constructor
     * @param socket socker
     * @param server server
     */
    public SocketClientConnection(Socket socket, Server server){
        this.server=server;
        this.socket=socket;
    }

    /**
     * getter for active
     * @return true if active, false otherwise
     */
    public synchronized boolean isActive(){
        return active;
    }

    /**
     * Getter for the ipAddress
     * @return the ipAddress
     */
    public String getIpAddress(){
        return socket.getRemoteSocketAddress().toString();
    }

    /**
     * Method used to send a message from the server to the client
     * @param message the message we want to send
     */
    public synchronized void send(Object message){
        try{
            out.reset();
            out.writeObject(message);
            out.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Closes the socket
     */
    public synchronized void closeConnection(){
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error while closing the connection");
        }
        active=false;
    }

    /**
     * Closes a connection and deregisters it from the server
     */
    public void close(){
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    /**
     * Asynchronously send a message to the client
     * @param message message
     */
    public void asyncSend(final Object message){
        new Thread((new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        })).start();
    }

    /**
     * Method that runs whenever the connection between the client and the server is estabilished.
     * It reads from the client its name and the number of players, and then adds the player to the lobby
     */
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
            server.lobby(this, name, nPlayers);
            while (isActive()){
                read=in.next();
                notify(read);
            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchElementException e){
            if(!isOver){
                if(server.getConn2s().contains(this)){
                    for (int i=0;i<server.getConn2s().size();i++){
                        server.getConn2s().get(i).close();
                    }
                }
                else if(server.getConn3s().contains(this)){
                    for (int i=0;i<server.getConn3s().size();i++){
                        server.getConn3s().get(i).close();
                    }
                }
            }
        }
        finally {
            close();
        }

    }
}
