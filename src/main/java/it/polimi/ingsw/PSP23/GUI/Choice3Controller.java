package it.polimi.ingsw.PSP23.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


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
            if(!Vars.god1Used){
                choiceImage1desc.setImage(new Image("/img/gods/" + Vars.god1 +"_desc.png"));
                choiceImage1.setImage(new Image ("/img/gods/" + Vars.god1 +".png"));
            }
            else{
                choiceButton1.setVisible(false);
            }
            if(!Vars.god2Used){
                choiceImage2desc.setImage(new Image("/img/gods/" + Vars.god2 +"_desc.png"));
                choiceImage2.setImage(new Image ("/img/gods/" + Vars.god2 +".png"));
            }
            else{
                choiceButton2.setVisible(false);
            }
            choiceButton3.setVisible(false);
        }
        if(Vars.numPlayer == 3){
            if(!Vars.god1Used){
                choiceImage1desc.setImage(new Image("/img/gods/" + Vars.god1 +"_desc.png"));
                choiceImage1.setImage(new Image ("/img/gods/" + Vars.god1 +".png"));
            }
            else{
                choiceButton1.setVisible(false);
            }
            if(!Vars.god2Used){
                choiceImage2desc.setImage(new Image("/img/gods/" + Vars.god2 +"_desc.png"));
                choiceImage2.setImage(new Image ("/img/gods/" + Vars.god2 +".png"));
            }
            else{
                choiceButton2.setVisible(false);
            }
            if(!Vars.god3Used){
                choiceImage3desc.setImage(new Image("/img/gods/" + Vars.god3 +"_desc.png"));
                choiceImage3.setImage(new Image ("/img/gods/" + Vars.god3 +".png"));
            }
            else{
                choiceButton3.setVisible(false);
            }
        }
    }

    @FXML
    public void choiceButton1Action(){
        Stage stage = (Stage) choiceButton1.getScene().getWindow();
        stage.close();
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god1);
        Vars.magicWrite.flush();
        Vars.god1Used = true;
    }
    @FXML
    public void choiceButton2Action(){
        Stage stage = (Stage) choiceButton2.getScene().getWindow();
        stage.close();
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god2);
        Vars.magicWrite.flush();
        Vars.god2Used = true;
    }
    @FXML
    public void choiceButton3Action(){
        Stage stage = (Stage) choiceButton3.getScene().getWindow();
        stage.close();
        Vars.magicWrite.println("CHOOSE_GOD:" + Vars.god3);
        Vars.magicWrite.flush();
        Vars.god3Used = true;
    }
}
