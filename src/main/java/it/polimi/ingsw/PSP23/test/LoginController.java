package it.polimi.ingsw.PSP23.test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    public void playButtonAction() {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();
        //player data
        System.out.println(username.getText());
        //default ip is localhost
        System.out.println(ip.getText());
        if (playersNumber2.isSelected()) {
            System.out.println("2");
            // TODO mandare comando connessione server
            try {
                Parent rootGameBoard = FXMLLoader.load(getClass().getResource("/gameBoard2.fxml"));
                Stage gameBoard = new Stage();
                gameBoard.setTitle("Santorini");
                gameBoard.setScene(new Scene(rootGameBoard));
                gameBoard.setResizable(false);
                gameBoard.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
                gameBoard.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (playersNumber3.isSelected()) {
            System.out.println("3");
            try {
                Parent rootGameBoard = FXMLLoader.load(getClass().getResource("/gameBoard3.fxml"));
                Stage gameBoard = new Stage();
                gameBoard.setTitle("Santorini");
                gameBoard.setScene(new Scene(rootGameBoard));
                gameBoard.setResizable(false);
                gameBoard.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
                gameBoard.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
