package it.polimi.ingsw.PSP23;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public final static int SOCKET_PORT=12345;
    public final static int MAX_NUM_CONNESSIONI=3;


    public static void main(String[] args) {
        ServerSocket socket;
        int numConnessioni=0;
        try {
            socket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }
        try{
            while(numConnessioni<MAX_NUM_CONNESSIONI){
                //accetta la connessione
                //associa ad ogni connessione un thread
                numConnessioni++;
                System.out.println(numConnessioni+" client connessi!");
            }
        }
        catch(Exception e){

        }
    }
}
