package it.polimi.ingsw.PSP23.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    Vars vars = new Vars();

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
        //System.out.println(username.getText());
        //default ip is localhost
        //System.out.println(ip.getText());
        vars.ipServer = ip.getText();
        vars.username = username.getText();
        if (playersNumber2.isSelected())
            vars.numPlayer = 2;
        else
            vars.numPlayer = 3;
        /*if (playersNumber2.isSelected()) {
            System.out.println("2");
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
        }*/
    }
}
