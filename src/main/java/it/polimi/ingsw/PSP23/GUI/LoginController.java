package it.polimi.ingsw.PSP23.GUI;

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
    public void playButtonAction() throws InterruptedException {
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.close();
        Vars.ipServer = ip.getText();
        Vars.username = username.getText();
        if (playersNumber2.isSelected())
            Vars.numPlayer = 2;
        else
            Vars.numPlayer = 3;
    }
}
