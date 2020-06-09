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

        }
    }

