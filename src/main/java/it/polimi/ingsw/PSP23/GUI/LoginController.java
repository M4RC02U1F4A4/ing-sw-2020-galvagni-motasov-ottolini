package it.polimi.ingsw.PSP23.GUI;

import it.polimi.ingsw.PSP23.client.ClientGUI;
import it.polimi.ingsw.PSP23.model.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;

public class LoginController {

    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    public void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private javafx.scene.control.Button playButton;
    @FXML
    private TextField username;
    @FXML
    private TextField ip;
    @FXML
    private RadioButton playersNumber2;
    @FXML
    private RadioButton playersNumber3;

    private boolean active = true;
    public synchronized boolean isActive(){
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @FXML
    public void playButtonAction() throws IOException {

        Vars.ipServer = ip.getText();
        Vars.username = username.getText();
        if (playersNumber2.isSelected())
            Vars.numPlayer = 2;
        else
            Vars.numPlayer = 3;

        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();

        ClientGUI c=new ClientGUI(Vars.ipServer,13245);
        Socket socket=new Socket(Vars.ipServer, 13245);
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
                                if(((String) inputObject).contains("GODSC")){
                                    String[] parts = ((String) inputObject).split("-");
                                    int k=0;
                                    //TODO: estrarre le divinità tra cui scegliere



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
        }catch (NoSuchElementException e){
            System.out.println("connection closed from client side");
        }finally {
            //socketIn.close();
            //Vars.magicWrite.close();
            //socket.close();
        }


        Vars.magicWrite.println(Vars.username);
        Vars.magicWrite.flush();
        Vars.magicWrite.println(String.valueOf(Vars.numPlayer));
        Vars.magicWrite.flush();
        while( !(Vars.serverMsg.contains("SELECT_GODS")) && !(Vars.serverMsg.contains("CHOOSE_GOD")) ) {try{Thread.sleep(1000);} catch (InterruptedException e){}}
        if(Vars.serverMsg.contains("SELECT_GODS")) {
            try{
                Parent rootChoice = FXMLLoader.load(getClass().getResource("/choice.fxml"));
                Stage choice = new Stage();
                choice.setTitle("Santorini - " + Vars.username);
                choice.setScene(new Scene(rootChoice));
                choice.setResizable(false);
                choice.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
                choice.show();
            } catch (IOException e) {e.printStackTrace();}
        }
        else if(Vars.serverMsg.contains("CHOOSE_GOD")){
            try {
                Parent rootChoice3 = FXMLLoader.load(getClass().getResource("/choice3.fxml"));
                Stage choice3 = new Stage();
                choice3.setTitle("Santorini - " + Vars.username);
                choice3.setScene(new Scene(rootChoice3));
                choice3.setResizable(false);
                choice3.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
                choice3.show();
            } catch (IOException e) { e.printStackTrace();}
        }


    }
}
