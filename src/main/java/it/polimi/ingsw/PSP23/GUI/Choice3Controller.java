package it.polimi.ingsw.PSP23.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Choice3Controller {
    Vars vars = new Vars();
    @FXML
    private ImageView choiceImage1desc;
    @FXML
    private ImageView choiceImage2desc;
    @FXML
    private ImageView choiceImage3desc;
    @FXML
    private ImageView choiceImage1;
    @FXML
    private ImageView choiceImage2;
    @FXML
    private ImageView choiceImage3;
    @FXML
    private Button choiceButton1;
    @FXML
    private Button choiceButton2;
    @FXML
    private Button choiceButton3;
    @FXML
    public void initialize(){
        //TODO: mostrare le divinità tra cui scegliere
        if(Vars.numPlayer == 2){
            choiceImage1desc.setImage(new Image("/img/gods/" + Vars.god1 +"_desc.png"));
            choiceImage3desc.setImage(new Image("/img/gods/" + Vars.god2 +"_desc.png"));
            choiceImage1.setImage(new Image ("/img/gods/" + Vars.god1 +".png"));
            choiceImage3.setImage(new Image ("/img/gods/" + Vars.god2 +".png"));
            choiceButton2.setVisible(false);
        }
        if(Vars.numPlayer == 3){
            choiceImage1desc.setImage(new Image("/img/gods/" + Vars.god1 +"_desc.png"));
            choiceImage2desc.setImage(new Image("/img/gods/" + Vars.god3 +"_desc.png"));
            choiceImage3desc.setImage(new Image("/img/gods/" + Vars.god2 +"_desc.png"));
            choiceImage1.setImage(new Image ("/img/gods/" + Vars.god1 +".png"));
            choiceImage2.setImage(new Image ("/img/gods/" + Vars.god3 +".png"));
            choiceImage3.setImage(new Image ("/img/gods/" + Vars.god2 +".png"));
        }
    }

    @FXML
    public void choiceButton1Action(){
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god1);
        Vars.magicWrite.flush();
    }
    @FXML
    public void choiceButton2Action(){
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god3);
        Vars.magicWrite.flush();
    }
    @FXML
    public void choiceButton3Action(){
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god2);
        Vars.magicWrite.flush();
    }
}
