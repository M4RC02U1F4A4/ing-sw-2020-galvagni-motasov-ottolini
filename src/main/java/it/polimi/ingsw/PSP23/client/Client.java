package it.polimi.ingsw.PSP23.client;

import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.Map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;
    private boolean active = true;

    /**Constructor
     * @param ip server's IP address
     * @param port server's listening port
     */
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * getter for active
     * @return true if active, false otherwise
     */
    public synchronized boolean isActive(){
        return active;
    }

    /**
     * Setter for active
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method thar recieves messages from the server.
     * If it recieves a normal text message, it writes it on screen
     * If it recieves a map, it draws it
     * If it recives "win"/"lose", it prints it and then it disconnects from the server
     * @param socketIn the stream that contains our messages
     * @return
     */
    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject instanceof String) {
                            if(inputObject.equals("LOSE")){
                                System.out.println("HAI PERSO");
                                System.exit(1);
                            }
                            else if(inputObject.equals("WIN")){
                                System.out.println("HAI VINTO");
                                System.exit(1);
                            }else if(inputObject.equals("Connection closed!")){
                                System.out.println("Connection closed!");
                                System.exit(1);
                            }

                            System.out.println((String) inputObject);
                        } else if (inputObject instanceof Map) {
                            ((Map) inputObject).drawMap();
                        }else if(inputObject instanceof Integer){
                            System.out.println((Integer) inputObject);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                }catch (Exception e){
                    setActive(false);
                    //System.exit(1);
                }
            }
        });
        t.start();
        return t;
    }

    /**
     * Method that reads a line fron an input buffer and writes is on the socket's output buffed
     * @param stdin the keyboard buffer
     * @param socketOut the buffer where we write the message
     * @return
     */
    public Thread asyncWriteToSocket(final Scanner stdin, final PrintWriter socketOut){
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(isActive()) {
                        String inputLine = stdin.nextLine();
                        socketOut.println(inputLine);
                        socketOut.flush();
                    }

                }catch (Exception e ){
                    setActive(false);

                }
            }
        });
        t.start();
        return t;
    }

    /**
     * Method that runs when a client is launched
     * @throws IOException
     */
    public void run() throws IOException{
        Socket socket=new Socket(ip, port);
        System.out.println("connessione stabilita");
        ObjectInputStream socketIn=new ObjectInputStream(socket.getInputStream());
        PrintWriter socketOut=new PrintWriter(socket.getOutputStream());
        Scanner stdin=new Scanner(System.in);

        try{
            Thread t0=asyncReadFromSocket(socketIn);
            Thread t1=asyncWriteToSocket(stdin,socketOut);
            t0.join();
            t1.join();
        }catch (InterruptedException| NoSuchElementException e){
            System.out.println("connection closed from client side");
        }finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
