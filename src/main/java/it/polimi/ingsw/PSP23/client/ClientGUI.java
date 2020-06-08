package it.polimi.ingsw.PSP23.client;

import it.polimi.ingsw.PSP23.GUI.GUIchoice;
import it.polimi.ingsw.PSP23.GUI.Main;
import it.polimi.ingsw.PSP23.GUI.MainTestChoice;
import it.polimi.ingsw.PSP23.GUI.Vars;
import it.polimi.ingsw.PSP23.model.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientGUI {
    private String ip;
    private int port;
    private boolean active = true;

    public ClientGUI(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public synchronized boolean isActive(){
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void run() throws IOException{
        Socket socket=new Socket(ip, port);
        System.out.println("connessione stabilita");
        ObjectInputStream socketIn=new ObjectInputStream(socket.getInputStream());
        Vars.magicWrite=new PrintWriter(socket.getOutputStream());

        try{
            new Thread((new Runnable() {
                @Override
                public void run() {
                    try {
                        while (isActive()) {
                            Object inputObject = socketIn.
                                    readObject();
                            if (inputObject instanceof String) {
                                System.out.println((String) inputObject);
                                if(((String) inputObject).contains("SELECT_GODS") || ((String) inputObject).contains("CHOOSE_GOD")){
                                    Vars.serverMsg = (String) inputObject;
                                    System.out.println("SALVATA");
                                }
                            }
                            else if (inputObject instanceof Map) {
                                ((Map) inputObject).drawMap();
                            }
                            else if(inputObject instanceof Integer){
                                System.out.println((Integer) inputObject);
                            }
                            else {
                                throw new IllegalArgumentException();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            })).start();
            Vars.magicWrite.println(Vars.username);
            Vars.magicWrite.flush();
            Vars.magicWrite.println(String.valueOf(Vars.numPlayer));
            Vars.magicWrite.flush();
            System.out.println("ENTRO NEL WHILE");
            while( !(Vars.serverMsg.contains("SELECT_GODS")) && !(Vars.serverMsg.contains("CHOOSE_GOD")) ) {try{Thread.sleep(1000);} catch (InterruptedException e){}}
            System.out.println("ESCO DAL WHILE");
            if(Vars.serverMsg.contains("SELECT_GODS")) {
                //aprire finestra scelta divinità
            }
            if(Vars.serverMsg.contains("CHOOSE_WORKER")){
                //aprire finestra scelta divinità da giocare
            }
            //qui si aprirà il tabellone di gioco
        }catch (NoSuchElementException e){
            System.out.println("connection closed from client side");
        }finally {
            //socketIn.close();
            //Vars.magicWrite.close();
            //socket.close();
        }
    }
}
