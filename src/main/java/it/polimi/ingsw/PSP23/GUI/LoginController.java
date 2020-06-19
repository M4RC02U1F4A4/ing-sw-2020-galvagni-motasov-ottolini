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
                                System.out.println("**************************");
                                if(((String) inputObject).contains("Attendi il tuo turno")) Vars.turnStatus = 0;
                                else if(((String) inputObject).contains("PLACE_WORKER")) Vars.turnStatus = 1;
                                else if(((String) inputObject).contains("MOVE")) Vars.turnStatus = 2;
                                else if(((String) inputObject).contains("BUILD")) Vars.turnStatus = 3;
                                else if(((String) inputObject).contains("CHOOSE_WORKER:")) Vars.turnStatus = 4;
                                if(((String) inputObject).contains("timeout")) Vars.gameStatus = 0;
                                if(((String) inputObject).contains("Comando non valido: riprova")) Vars.commandNotValid = true;
                                if(((String) inputObject).contains("WIN")) Vars.statusWinLose = 1;
                                else if(((String) inputObject).contains("LOOSE")) Vars.statusWinLose = 0;
                                if(((String) inputObject).contains("SELECT_GODS") || ((String) inputObject).contains("CHOOSE_GOD") || ((String) inputObject).contains("STARTING THE GAME")){
                                    Vars.serverMsg = (String) inputObject;
                                }
                                if(((String) inputObject).contains("TURN:")){
                                    Vars.currentPlayer = ((String) inputObject).replace("TURN:", "");
                                }
                                if(((String) inputObject).contains("GODSC")){
                                    String tempString = ((String) inputObject).replace("GODSC:", "");
                                    String[] parts = tempString.split("-");
                                    if(Vars.numPlayer == 2){
                                        Vars.god1 = parts[0];
                                        Vars.god2 = parts[1];
                                    }
                                    else if(Vars.numPlayer == 3){
                                        Vars.god1 = parts[0];
                                        Vars.god2 = parts[1];
                                        Vars.god3 = parts[2];
                                    }
                                }
                                if(((String) inputObject).contains("Scegli un dio tra quelli disponibili:")){
                                    String tempString = ((String) inputObject).replace("Scegli un dio tra quelli disponibili:", "");
                                    if(!tempString.contains(Vars.god1)){Vars.god1Used = true;}
                                    if(!tempString.contains(Vars.god2)){Vars.god2Used = true;}
                                    if(!tempString.contains(Vars.god3)){Vars.god3Used = true;}
                                }
                                if(((String) inputObject).contains("PLAYER1:")){
                                    String tempString = (String) inputObject;
                                    tempString = tempString.replace("\n", "");
                                    tempString = tempString.replace("PLAYER1:", "");
                                    tempString = tempString.replace("PLAYER2:", "-");
                                    tempString = tempString.replace("PLAYER3:", "-");
                                    String[] parts = tempString.split("-");
                                    if(Vars.numPlayer == 2){
                                        Vars.player1Name = parts[0];
                                        Vars.player1God = parts[2];
                                        Vars.player2Name = parts[3];
                                        Vars.player2God = parts[5];
                                    }
                                    else if(Vars.numPlayer == 3){
                                        Vars.player1Name = parts[0];
                                        Vars.player1God = parts[2];
                                        Vars.player2Name = parts[3];
                                        Vars.player2God = parts[5];
                                        Vars.player3Name = parts[6];
                                        Vars.player3God = parts[8];
                                    }
                                }
                            }
                            else if (inputObject instanceof Map) {
                                //((Map) inputObject).drawMap();
                                Vars.map = (Map) inputObject;
                                Vars.mapEdit = true;
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
        while( !(Vars.serverMsg.contains("SELECT_GODS")) && !(Vars.serverMsg.contains("CHOOSE_GOD")) ) {try{Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}}
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
