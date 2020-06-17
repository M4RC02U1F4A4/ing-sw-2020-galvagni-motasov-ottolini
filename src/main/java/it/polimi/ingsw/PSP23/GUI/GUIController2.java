package it.polimi.ingsw.PSP23.GUI;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Status;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class GUIController2 {
    //PLAYER 1 BLUE
    //PLAYER 2 RED
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
    private Label player1Power;
    @FXML
    private Label player2Power;
    @FXML
    private Label playerCurrentPlay;
    @FXML
    private ImageView player1God;
    @FXML
    private ImageView player2God;
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
    public void initialize() throws IOException{
        player1Username.setText(Vars.player1Name);
        player2Username.setText(Vars.player2Name);
        player1God.setImage(new Image ("/img/gods/" + Vars.player1God +".png"));
        player2God.setImage(new Image ("/img/gods/" + Vars.player2God +".png"));
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

        //check Vars.myGod for set utiliry button visibility

        playerCurrentPlay.setText(Vars.player1Name);
        errorButton.setVisible(false);

        Thread thread = new Thread(() -> {
            while(true) {
                try{Thread.sleep(250);}catch (InterruptedException e){e.printStackTrace();}
                if(Vars.mapEdit) {
                    Platform.runLater(() -> updateGUIMap(Vars.map));
                    Vars.mapEdit = false;
                }

            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    private void utilityButtonAction(){
        System.out.println("ACTION");
    }

    public void errorButtonAction(){
        errorButton.setVisible(false);
    }

    public void worker1Action(){
        if(Vars.turnStatus == 4){
            Vars.magicWrite.println("CHOOSE_WORKER:0");
            Vars.magicWrite.flush();
        }
        else errorButton.setVisible(true);
    }
    public void worker2Action(){
        if(Vars.turnStatus == 4){
            Vars.magicWrite.println("CHOOSE_WORKER:1");
            Vars.magicWrite.flush();
        }
        else errorButton.setVisible(true);
    }

    public void press(Button b, int x, int y){
        if(Vars.turnStatus == 0){
            //not your turn
            errorButton.setVisible(true);
        }
        else if(Vars.turnStatus == 1){
            //place worker
            Vars.magicWrite.println("PLACE_WORKER:" + x + "," + y);
            Vars.magicWrite.flush();
        }
        else if(Vars.turnStatus == 2){
            //move
            Vars.magicWrite.println("MOVE:" + x + "," + y);
            Vars.magicWrite.flush();
        }
        else if(Vars.turnStatus == 3){
            //build
            if(blocco.isSelected()){
                Vars.magicWrite.println("BUILD:" + x + "," + y + "," + "blocco");
                Vars.magicWrite.flush();
            }
            else if(cupola.isSelected())
                Vars.magicWrite.println("BUILD:" + x + "," + y + "," + "CUPOLA");
            Vars.magicWrite.flush();
        }
    }

    public void updateGUIMap(Map map){
        playerCurrentPlay.setText(Vars.currentPlayer);
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                if(map.getCell(x, y).height() == 0){
                    if(map.getCell(x, y).isOccupied()){
                        if(map.getCell(x, y).getWorker().getColor().equals(Color.RED)){
                            updateCell("red", x, y);
                        }
                        else if(map.getCell(x, y).getWorker().getColor().equals(Color.BLUE)){
                            updateCell("blue", x, y);
                        }
                        else if(map.getCell(x, y).getWorker().getColor().equals(Color.WHITE)){
                            updateCell("white", x, y);
                        }
                    }
                    else updateCell("NULL", x, y);

                }
                else {
                    if(map.getCell(x, y).levelStatus(3).equals(Status.CUPOLA)){
                        //lvl2+c
                        updateCell("lvl2+c", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(2).equals(Status.BUILT)){
                        //lvl2
                        if(map.getCell(x, y).isOccupied()){
                            if(map.getCell(x, y).getWorker().getColor().equals(Color.RED)){
                                updateCell("lvl2+red", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.BLUE)){
                                updateCell("lvl2+blue", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.WHITE)){
                                updateCell("lvl2+white", x, y);
                            }
                        }
                        else updateCell("lvl2", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(2).equals(Status.CUPOLA)){
                        //lvl1+c
                        updateCell("lvl1+c", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(1).equals(Status.BUILT)){
                        //lvl1
                        if(map.getCell(x, y).isOccupied()){
                            if(map.getCell(x, y).getWorker().getColor().equals(Color.RED)){
                                updateCell("lvl1+red", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.BLUE)){
                                updateCell("lvl1+blue", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.WHITE)){
                                updateCell("lvl1+white", x, y);
                            }
                        }
                        else updateCell("lvl1", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(1).equals(Status.CUPOLA)){
                        //lvl0+c
                        updateCell("lvl0+c", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(0).equals(Status.BUILT)){
                        //lvl0
                        if(map.getCell(x, y).isOccupied()){
                            if(map.getCell(x, y).getWorker().getColor().equals(Color.RED)){
                                updateCell("lvl0+red", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.BLUE)){
                                updateCell("lvl0+blue", x, y);
                            }
                            else if(map.getCell(x, y).getWorker().getColor().equals(Color.WHITE)){
                                updateCell("lvl0+white", x, y);
                            }
                        }
                        else updateCell("lvl0", x, y);
                    }
                    else if(map.getCell(x, y).levelStatus(0).equals(Status.CUPOLA)){
                        //c
                        updateCell("c", x, y);
                    }
                }
            }
        }
    }

    private void updateCell(String imgName, int x, int y){
        if(x == 0){
            if(y == 0){
                if(imgName.equals("c")) b00.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b00.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b00.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b00.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b00.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b00.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b00.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b00.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b00.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b00.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b00.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b00.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b00.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b00.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b00.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b00.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b00.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b00.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b00.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b00.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 1){
                if(imgName.equals("c")) b01.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b01.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b01.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b01.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b01.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b01.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b01.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b01.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b01.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b01.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b01.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b01.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b01.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b01.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b01.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b01.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b01.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b01.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b01.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b01.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 2){
                if(imgName.equals("c")) b02.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b02.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b02.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b02.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b02.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b02.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b02.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b02.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b02.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b02.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b02.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b02.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b02.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b02.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b02.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b02.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b02.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b02.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b02.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b02.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 3){
                if(imgName.equals("c")) b03.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b03.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b03.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b03.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b03.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b03.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b03.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b03.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b03.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b03.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b03.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b03.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b03.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b03.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b03.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b03.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b03.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b03.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b03.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b03.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 4){
                if(imgName.equals("c")) b04.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b04.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b04.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b04.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b04.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b04.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b04.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b04.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b04.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b04.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b04.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b04.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b04.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b04.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b04.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b04.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b04.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b04.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b04.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b04.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
        }
        else if(x == 1){
            if(y == 0){
                if(imgName.equals("c")) b10.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b10.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b10.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b10.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b10.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b10.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b10.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b10.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b10.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b10.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b10.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b10.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b10.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b10.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b10.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b10.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b10.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b10.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b10.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b10.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 1){
                if(imgName.equals("c")) b11.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b11.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b11.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b11.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b11.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b11.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b11.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b11.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b11.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b11.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b11.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b11.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b11.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b11.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b11.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b11.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b11.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b11.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b11.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b11.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 2){
                if(imgName.equals("c")) b12.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b12.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b12.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b12.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b12.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b12.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b12.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b12.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b12.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b12.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b12.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b12.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b12.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b12.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b12.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b12.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b12.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b12.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b12.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b12.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 3){
                if(imgName.equals("c")) b13.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b13.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b13.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b13.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b13.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b13.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b13.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b13.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b13.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b13.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b13.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b13.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b13.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b13.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b13.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b13.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b13.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b13.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b13.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b13.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 4){
                if(imgName.equals("c")) b14.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b14.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b14.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b14.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b14.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b14.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b14.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b14.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b14.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b14.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b14.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b14.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b14.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b14.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b14.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b14.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b14.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b14.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b14.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b14.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
        }
        else if(x == 2){
            if(y == 0){
                if(imgName.equals("c")) b20.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b20.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b20.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b20.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b20.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b20.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b20.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b20.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b20.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b20.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b20.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b20.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b20.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b20.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b20.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b20.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b20.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b20.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b20.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b20.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 1){
                if(imgName.equals("c")) b21.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b21.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b21.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b21.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b21.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b21.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b21.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b21.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b21.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b21.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b21.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b21.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b21.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b21.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b21.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b21.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b21.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b21.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b21.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b21.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 2){
                if(imgName.equals("c")) b22.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b22.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b22.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b22.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b22.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b22.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b22.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b22.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b22.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b22.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b22.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b22.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b22.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b22.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b22.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b22.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b22.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b22.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b22.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b22.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 3){
                if(imgName.equals("c")) b23.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b23.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b23.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b23.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b23.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b23.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b23.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b23.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b23.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b23.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b23.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b23.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b23.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b23.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b23.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b23.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b23.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b23.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b23.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b23.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 4){
                if(imgName.equals("c")) b24.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b24.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b24.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b24.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b24.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b24.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b24.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b24.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b24.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b24.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b24.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b24.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b24.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b24.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b24.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b24.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b24.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b24.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b24.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b24.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
        }
        else if(x == 3){
            if(y == 0){
                if(imgName.equals("c")) b30.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b30.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b30.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b30.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b30.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b30.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b30.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b30.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b30.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b30.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b30.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b30.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b30.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b30.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b30.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b30.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b30.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b30.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b30.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b30.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 1){
                if(imgName.equals("c")) b31.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b31.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b31.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b31.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b31.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b31.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b31.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b31.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b31.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b31.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b31.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b31.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b31.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b31.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b31.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b31.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b31.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b31.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b31.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b31.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 2){
                if(imgName.equals("c")) b32.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b32.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b32.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b32.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b32.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b32.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b32.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b32.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b32.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b32.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b32.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b32.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b32.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b32.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b32.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b32.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b32.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b32.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b32.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b32.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 3){
                if(imgName.equals("c")) b33.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b33.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b33.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b33.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b33.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b33.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b33.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b33.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b33.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b33.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b33.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b33.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b33.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b33.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b33.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b33.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b33.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b33.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b33.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b33.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 4){
                if(imgName.equals("c")) b34.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b34.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b34.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b34.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b34.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b34.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b34.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b34.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b34.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b34.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b34.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b34.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b34.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b34.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b34.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b34.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b34.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b34.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b34.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b34.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
        }
        else if(x == 4){
            if(y == 0){
                if(imgName.equals("c")) b40.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b40.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b40.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b40.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b40.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b40.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b40.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b40.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b40.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b40.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b40.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b40.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b40.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b40.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b40.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b40.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b40.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b40.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b40.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b40.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 1){
                if(imgName.equals("c")) b41.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b41.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b41.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b41.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b41.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b41.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b41.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b41.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b41.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b41.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b41.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b41.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b41.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b41.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b41.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b41.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b41.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b41.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b41.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b41.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 2){
                if(imgName.equals("c")) b42.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b42.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b42.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b42.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b42.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b42.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b42.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b42.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b42.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b42.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b42.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b42.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b42.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b42.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b42.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b42.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b42.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b42.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b42.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b42.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 3){
                if(imgName.equals("c")) b43.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b43.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b43.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b43.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b43.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b43.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b43.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b43.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b43.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b43.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b43.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b43.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b43.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b43.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b43.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b43.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b43.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b43.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b43.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b43.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
            else if(y == 4){
                if(imgName.equals("c")) b44.setStyle("-fx-background-image: url('/img/c.png')");
                else if(imgName.equals("lvl0")) b44.setStyle("-fx-background-image: url('/img/lvl0.png')");
                else if(imgName.equals("lvl0+c")) b44.setStyle("-fx-background-image: url('/img/lvl0+c.png')");
                else if(imgName.equals("lvl1")) b44.setStyle("-fx-background-image: url('/img/lvl1.png')");
                else if(imgName.equals("lvl1+c")) b44.setStyle("-fx-background-image: url('/img/lvl1+c.png')");
                else if(imgName.equals("lvl2")) b44.setStyle("-fx-background-image: url('/img/lvl2.png')");
                else if(imgName.equals("lvl2+c")) b44.setStyle("-fx-background-image: url('/img/lvl2+c.png')");
                else if(imgName.equals("lvl0+red")) b44.setStyle("-fx-background-image: url('/img/lvl0+red.png')");
                else if(imgName.equals("lvl0+blue")) b44.setStyle("-fx-background-image: url('/img/lvl0+blue.png')");
                else if(imgName.equals("lvl0+white")) b44.setStyle("-fx-background-image: url('/img/lvl0+white.png')");
                else if(imgName.equals("lvl1+red")) b44.setStyle("-fx-background-image: url('/img/lvl1+red.png')");
                else if(imgName.equals("lvl1+blue")) b44.setStyle("-fx-background-image: url('/img/lvl1+blue.png')");
                else if(imgName.equals("lvl1+white")) b44.setStyle("-fx-background-image: url('/img/lvl1+white.png')");
                else if(imgName.equals("lvl2+red")) b44.setStyle("-fx-background-image: url('/img/lvl2+red.png')");
                else if(imgName.equals("lvl2+blue")) b44.setStyle("-fx-background-image: url('/img/lvl2+blue.png')");
                else if(imgName.equals("lvl2+white")) b44.setStyle("-fx-background-image: url('/img/lvl2+white.png')");
                else if(imgName.equals("red")) b44.setStyle("-fx-background-image: url('/img/red.png')");
                else if(imgName.equals("blue")) b44.setStyle("-fx-background-image: url('/img/blue.png')");
                else if(imgName.equals("white")) b44.setStyle("-fx-background-image: url('/img/white.png')");
                else if(imgName.equals("NULL")) b44.setStyle("-fx-background-image: url('/img/NULL.png')");
            }
        }
    }

    @FXML
    public void b00Action(){
        press(b00,0,0);
    }
    public void b01Action(){
        press(b01,0,1);
    }
    public void b02Action(){
        press(b02,0,2);
    }
    public void b03Action(){
        press(b03,0,3);
    }
    public void b04Action(){
        press(b04,0,4);
    }

    public void b10Action(){
        press(b10,1,0);
    }
    public void b11Action(){
        press(b11,1,1);
    }
    public void b12Action(){
        press(b12,1,2);
    }
    public void b13Action(){
        press(b13,1,3);
    }
    public void b14Action(){
        press(b14,1,4);
    }

    public void b20Action(){
        press(b20,2,0);
    }
    public void b21Action(){
        press(b21,2,1);
    }
    public void b22Action(){
        press(b22,2,2);
    }
    public void b23Action(){
        press(b23,2,3);
    }
    public void b24Action(){
        press(b24,2,4);
    }

    public void b30Action(){
        press(b30,3,0);
    }
    public void b31Action(){
        press(b31,3,1);
    }
    public void b32Action(){
        press(b32,3,2);
    }
    public void b33Action(){
        press(b33,3,3);
    }
    public void b34Action(){
        press(b34,3,4);
    }

    public void b40Action(){
        press(b40,4,0);
    }
    public void b41Action(){
        press(b41,4,1);
    }
    public void b42Action() {
        press(b42,4,2);
    }
    public void b43Action() {
        press(b43,4,3);
    }
    public void b44Action() {
        press(b44,4,4);
    }
}
/*
VITTORIA
Stage stage = (Stage) b44.getScene().getWindow();
        stage.close();
        try {
            Parent rootWin = FXMLLoader.load(getClass().getResource("/win.fxml"));
            Stage win = new Stage();
            win.setTitle("Santorini");
            win.setScene(new Scene(rootWin));
            win.setResizable(false);
            win.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
            win.show();
        } catch (IOException e) { e.printStackTrace(); }
 */
/*
SCONFITTA
Stage stage = (Stage) b43.getScene().getWindow();
        stage.close();
        try {
            Parent rootWin = FXMLLoader.load(getClass().getResource("/lose.fxml"));
            Stage lose = new Stage();
            lose.setTitle("Santorini");
            lose.setScene(new Scene(rootWin));
            lose.setResizable(false);
            lose.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
            lose.show();
        } catch (IOException e) { e.printStackTrace(); }
 */

//b31.setStyle("-fx-background-image: url('/img/blue.png')");