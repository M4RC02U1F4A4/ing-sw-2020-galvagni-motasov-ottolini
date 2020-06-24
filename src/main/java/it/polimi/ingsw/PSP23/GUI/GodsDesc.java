package it.polimi.ingsw.PSP23.GUI;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GodsDesc {
    @FXML
    private ImageView descImg;

    @FXML
    public void initialize(){
        switch (Vars.godChoice){
            case "apollo":
                descImg.setImage(new Image("/img/gods/Apollo_desc.png"));
                break;
            case "hera":
                descImg.setImage(new Image("/img/gods/Hera_desc.png"));
                break;
            case "prometheus":
                descImg.setImage(new Image("/img/gods/Prometheus_desc.png"));
                break;
            case "artemis":
                descImg.setImage(new Image("/img/gods/Artemis_desc.png"));
                break;
            case "triton":
                descImg.setImage(new Image("/img/gods/Triton_desc.png"));
                break;
            case "zeus":
                descImg.setImage(new Image("/img/gods/Zeus_desc.png"));
                break;
            case "minotaur":
                descImg.setImage(new Image("/img/gods/Minotaur_desc.png"));
                break;
            case "demeter":
                descImg.setImage(new Image("/img/gods/Demeter_desc.png"));
                break;
            case "athena":
                descImg.setImage(new Image("/img/gods/Athena_desc.png"));
                break;
            case "pan":
                descImg.setImage(new Image("/img/gods/Pan_desc.png"));
                break;
            case "chronus":
                descImg.setImage(new Image("/img/gods/Chronus_desc.png"));
                break;
            case "hestia":
                descImg.setImage(new Image("/img/gods/Hestia_desc.png"));
                break;
            case "hephaestus":
                descImg.setImage(new Image("/img/gods/Hephaestus_desc.png"));
                break;
            case "atlas":
                descImg.setImage(new Image("/img/gods/Atlas_desc.png"));
                break;
        }
    }
}
