package it.polimi.ingsw.PSP23.test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController {

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
    @FXML
    public void playButtonAction() {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();

        //player data

        //TODO: l'username se non inserito va generato random
        System.out.println(username.getText());
        //default ip is localhost
        System.out.println(ip.getText());
        if (playersNumber2.isSelected()) System.out.println("2");
        if (playersNumber3.isSelected()) System.out.println("3");

        //gameboard
        try{
            Parent rootGameBoard = FXMLLoader.load(getClass().getResource("/gameBoard3.fxml"));
            Stage gameBoard = new Stage();
            gameBoard.setTitle("Santorini");
            gameBoard.setScene(new Scene(rootGameBoard));
            gameBoard.setResizable(false);
            gameBoard.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
            gameBoard.show();
        } catch (IOException e){e.printStackTrace();}
    }

    @FXML
    private Button b00, b01, b02, b03, b04,
                   b10, b11, b12, b13, b14,
                   b20, b21, b22, b23, b24,
                   b30, b31, b32, b33, b34,
                   b40, b41, b42, b43, b44;

    public void press(Button b){
        b.setStyle("-fx-background-image: url('/img/lvl1.png')");
    }

    @FXML
    public void b00Action(){
        press(b00);
    }
    public void b01Action(){
        press(b01);
    }
    public void b02Action(){
        press(b02);
    }
    public void b03Action(){
        press(b03);
    }
    public void b04Action(){
        press(b04);
    }

    public void b10Action(){
        press(b10);
    }
    public void b11Action(){
        press(b11);
    }
    public void b12Action(){
        press(b12);
    }
    public void b13Action(){
        press(b13);
    }
    public void b14Action(){
        press(b14);
    }

    public void b20Action(){
        press(b20);
    }
    public void b21Action(){
        press(b21);
    }
    public void b22Action(){
        press(b22);
    }
    public void b23Action(){
        press(b23);
    }
    public void b24Action(){
        press(b24);
    }

    public void b30Action(){
        press(b30);
    }
    public void b31Action(){
        press(b31);
    }
    public void b32Action(){
        press(b32);
    }
    public void b33Action(){
        press(b33);
    }
    public void b34Action(){
        press(b34);
    }

    public void b40Action(){
        press(b40);
    }
    public void b41Action(){
        press(b41);
    }
    public void b42Action(){
        press(b42);
    }
    public void b43Action(){
        press(b43);
    }
    public void b44Action(){
        press(b44);
    }

}

