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
        choiceImage1desc.setImage(new Image("/img/gods/" + vars.god1 +"_desc.png"));
        choiceImage2desc.setImage(new Image("/img/gods/" + vars.god2 +"_desc.png"));
        choiceImage3desc.setImage(new Image("/img/gods/" + vars.god3 +"_desc.png"));
        choiceImage1.setImage(new Image ("/img/gods/" + vars.god1 +".png"));
        choiceImage2.setImage(new Image ("/img/gods/" + vars.god2 +".png"));
        choiceImage3.setImage(new Image ("/img/gods/" + vars.god3 +".png"));
    }

    @FXML
    public void choiceButton1Action(){
        System.out.println(vars.god1);
    }
    @FXML
    public void choiceButton2Action(){
        System.out.println(vars.god2);
    }
    @FXML
    public void choiceButton3Action(){
        System.out.println(vars.god3);
    }
}
