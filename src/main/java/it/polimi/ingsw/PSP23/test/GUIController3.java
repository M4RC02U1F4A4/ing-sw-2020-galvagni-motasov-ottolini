package it.polimi.ingsw.PSP23.test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class GUIController3 {

    @FXML
    private Label player1Username;
    @FXML
    private Label player2Username;
    @FXML
    private Label player3Username;
    @FXML
    private Label player1Power;
    @FXML
    private Label player2Power;
    @FXML
    private Label player3Power;
    @FXML
    private Label playerCurrentPlay;
    @FXML
    private ImageView player1God;
    @FXML
    private ImageView player2God;
    @FXML
    private ImageView player3God;
    @FXML
    private ImageView player3;
    @FXML
    private Button loadingButton;
    @FXML
    private Button errorButton;
    @FXML
    private Button b00, b01, b02, b03, b04,
            b10, b11, b12, b13, b14,
            b20, b21, b22, b23, b24,
            b30, b31, b32, b33, b34,
            b40, b41, b42, b43, b44;
    @FXML
    private Button utilityButton;


    @FXML
    private void utilityButtonAction(){
        System.out.println("ACTION");
    }

    @FXML
    public void loadingButtonAction(){
        player1Username.setText("Player1");
        player2Username.setText("Player2");
        player3Username.setText("Player3");
        player1God.setImage(new Image ("/img/gods/Artemis.png"));
        player2God.setImage(new Image ("/img/gods/Prometheus.png"));
        player3God.setImage(new Image ("/img/gods/Minotaur.png"));
        player1Power.setText("Your Worker may move one additional time, but not back to its initial space.");
        player2Power.setText("If your Worker does not move up, it may build both before and after moving.");
        player3Power.setText("Your Worker may move into an opponent Worker’s space, if their Worker can be forced one space straight backwards to an unoccupied space at any level.");
        playerCurrentPlay.setText("Marco");
        loadingButton.setVisible(false);
        errorButton.setVisible(false);
    }

    public void errorButtonAction(){
        errorButton.setVisible(false);
    }

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
        b31.setStyle("-fx-background-image: url('/img/blue.png')");
    }
    public void b32Action(){
        b32.setStyle("-fx-background-image: url('/img/red.png')");
    }
    public void b33Action(){
        b33.setStyle("-fx-background-image: url('/img/white.png')");
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
        //test
        errorButton.setVisible(true);
    }
    public void b43Action(){
        press(b43);
        //test
        Stage stage = (Stage) b43.getScene().getWindow();
        //stage.close();
        try {
            Parent rootWin = FXMLLoader.load(getClass().getResource("/lose.fxml"));
            Stage lose = new Stage();
            lose.setTitle("Santorini");
            lose.setScene(new Scene(rootWin));
            lose.setResizable(false);
            lose.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
            lose.show();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void b44Action(){
        press(b44);
        //test
        Stage stage = (Stage) b44.getScene().getWindow();
        //stage.close();
        try {
            Parent rootWin = FXMLLoader.load(getClass().getResource("/win.fxml"));
            Stage win = new Stage();
            win.setTitle("Santorini");
            win.setScene(new Scene(rootWin));
            win.setResizable(false);
            win.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
            win.show();
        } catch (IOException e) { e.printStackTrace(); }
    }
}


