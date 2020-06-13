package it.polimi.ingsw.PSP23.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIController3 {
    //PLAYER 1 RED
    //PLAYER 2 BLUE
    //PLAYER 3 WHITE
    private String hera = "HERA\n\nAn opponent cannot win by moving into a perimeter space.";
    private String prometheus = "PROMETHEUS\n\nIf your Worker does not move up, it may build both before and after moving.";
    private String artemis = "ARTEMIS\n\nYour Worker may move one additional time, but not back to its initial space.";
    private String triton = "TRITON\n\nEach time your Worker moves into a perimeter space, it may immediately move again.";
    private String zeus = "ZEUS\n\nYour Worker may build a block under itself.";
    private String minotaur = "MINOTAUR\n\nYour Worker may move into an opponent Worker’s space, if their Worker can be forced one space straight backwards to an unoccupied space at any level.";
    private String demeter = "DEMETER\n\nYour Worker may build one additional time, but not on the same space.";
    private String athena = "ATHENA\n\nIf one of your Workers moved up on your last turn, opponent Workers cannot move up this turn.";
    private String pan = "PAN\n\nYou also win if your Worker moves down two or more levels.";
    private String chronus = "CHRONUS\n\nYou also win when there are at least five Complete Towers on the board.";
    private String hestia = "HESTIA\n\nYour Worker may build one additional time, but this cannot be on a perimeter space.";
    private String hephaestus = "HEPHAESTUS\n\nYour Worker may build one additional block (not dome) on top of your first block.";
    private String apollo = "APOLLO\n\nYour Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated.";
    private String atlas = "ATLAS\n\nYour Worker may build a dome at any level.";

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
    private Button worker1;
    @FXML
    private Button worker2;
    @FXML
    private RadioButton blocco;
    @FXML
    private RadioButton cupola;

    @FXML
    public void initialize(){
        player1Username.setText(Vars.player1Name);
        player2Username.setText(Vars.player2Name);
        player3Username.setText(Vars.player3Name);
        player1God.setImage(new Image ("/img/gods/" + Vars.player1God +".png"));
        player2God.setImage(new Image ("/img/gods/" + Vars.player2God +".png"));
        player3God.setImage(new Image ("/img/gods/" + Vars.player3God +".png"));
        //TODO: migliorare questa parte
        //Player 1
        if(Vars.player1God.equals("Hera")) player1Power.setText(hera);
        if(Vars.player1God.equals("Prometheus")) player1Power.setText(prometheus);
        if(Vars.player1God.equals("Artemis")) player1Power.setText(artemis);
        if(Vars.player1God.equals("Triton")) player1Power.setText(triton);
        if(Vars.player1God.equals("Zeus")) player1Power.setText(zeus);
        if(Vars.player1God.equals("Minotaur")) player1Power.setText(minotaur);
        if(Vars.player1God.equals("Demeter")) player1Power.setText(demeter);
        if(Vars.player1God.equals("Athena")) player1Power.setText(athena);
        if(Vars.player1God.equals("Pan")) player1Power.setText(pan);
        if(Vars.player1God.equals("Chronus")) player1Power.setText(chronus);
        if(Vars.player1God.equals("Hestia")) player1Power.setText(hestia);
        if(Vars.player1God.equals("Hephaestus")) player1Power.setText(hephaestus);
        if(Vars.player1God.equals("Apollo")) player1Power.setText(apollo);
        if(Vars.player1God.equals("Atlas")) player1Power.setText(atlas);
        //Player 2
        if(Vars.player2God.equals("Hera")) player2Power.setText(hera);
        if(Vars.player2God.equals("Prometheus")) player2Power.setText(prometheus);
        if(Vars.player2God.equals("Artemis")) player2Power.setText(artemis);
        if(Vars.player2God.equals("Triton")) player2Power.setText(triton);
        if(Vars.player2God.equals("Zeus")) player2Power.setText(zeus);
        if(Vars.player2God.equals("Minotaur")) player2Power.setText(minotaur);
        if(Vars.player2God.equals("Demeter")) player2Power.setText(demeter);
        if(Vars.player2God.equals("Athena")) player2Power.setText(athena);
        if(Vars.player2God.equals("Pan")) player2Power.setText(pan);
        if(Vars.player2God.equals("Chronus")) player2Power.setText(chronus);
        if(Vars.player2God.equals("Hestia")) player2Power.setText(hestia);
        if(Vars.player2God.equals("Hephaestus")) player2Power.setText(hephaestus);
        if(Vars.player2God.equals("Apollo")) player2Power.setText(apollo);
        if(Vars.player2God.equals("Atlas")) player2Power.setText(atlas);
        //Player 3
        if(Vars.player3God.equals("Hera")) player3Power.setText(hera);
        if(Vars.player3God.equals("Prometheus")) player3Power.setText(prometheus);
        if(Vars.player3God.equals("Artemis")) player3Power.setText(artemis);
        if(Vars.player3God.equals("Triton")) player3Power.setText(triton);
        if(Vars.player3God.equals("Zeus")) player3Power.setText(zeus);
        if(Vars.player3God.equals("Minotaur")) player3Power.setText(minotaur);
        if(Vars.player3God.equals("Demeter")) player3Power.setText(demeter);
        if(Vars.player3God.equals("Athena")) player3Power.setText(athena);
        if(Vars.player3God.equals("Pan")) player3Power.setText(pan);
        if(Vars.player3God.equals("Chronus")) player3Power.setText(chronus);
        if(Vars.player3God.equals("Hestia")) player3Power.setText(hestia);
        if(Vars.player3God.equals("Hephaestus")) player3Power.setText(hephaestus);
        if(Vars.player3God.equals("Apollo")) player3Power.setText(apollo);
        if(Vars.player3God.equals("Atlas")) player3Power.setText(atlas);

        if(Vars.player1Name.equals(Vars.username)) System.out.println("SEI IL GIOCATORE 1");
        if(Vars.player2Name.equals(Vars.username)) System.out.println("SEI IL GIOCATORE 2");
        if(Vars.player3Name.equals(Vars.username)) System.out.println("SEI IL GIOCATORE 3");

        playerCurrentPlay.setText(Vars.username);
        errorButton.setVisible(false);
    }

    @FXML
    private void utilityButtonAction(){
        System.out.println("ACTION");
    }

    public void errorButtonAction(){
        errorButton.setVisible(false);
    }

    public void worker1Action(){
        Vars.magicWrite.println("CHOOSE_WORKER:0");
        Vars.magicWrite.flush();
    }
    public void worker2Action(){
        Vars.magicWrite.println("CHOOSE_WORKER:1");
        Vars.magicWrite.flush();
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


