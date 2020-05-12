package it.polimi.ingsw.PSP23.test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceController {
    @FXML
    private Button apollo;
    @FXML
    private Button hera;
    @FXML
    private Button prometheus;
    @FXML
    private Button artemis;
    @FXML
    private Button triton;
    @FXML
    private Button zeus;
    @FXML
    private Button minotaur;
    @FXML
    private Button demeter;
    @FXML
    private Button athena;
    @FXML
    private Button pan;
    @FXML
    private Button chronus;
    @FXML
    private Button hestia;
    @FXML
    private Button hephaestus;
    @FXML
    private Button atlas;

    @FXML
    public void apolloAction(){description("apollo");}
    @FXML
    public void heraAction(){description("hera");}
    @FXML
    public void prometheusAction(){description("prometheus");}
    @FXML
    public void artemisAction(){description("artemis");}
    @FXML
    public void tritonAction(){description("triton");}
    @FXML
    public void zeusAction(){description("zeus");}
    @FXML
    public void minotaurAction(){description("minotaur");}
    @FXML
    public void demeterAction(){description("demeter");}
    @FXML
    public void athenaAction(){description("athena");}
    @FXML
    public void panAction(){description("pan");}
    @FXML
    public void chronusAction(){description("chronus");}
    @FXML
    public void hestiaAction(){description("hestia");}
    @FXML
    public void hephaestusAction(){description("hephaestus");}
    @FXML
    public void atlasAction(){description("atlas");}

    public void description(String god){
        Stage window = new Stage();
        window.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(god);
        window.setMinWidth(640);
        window.setWidth(640);
        window.setMaxWidth(640);
        window.setMinHeight(520);
        window.setHeight(520);
        window.setMaxHeight(520);
        window.setResizable(false);
        Pane layout = new Pane();
        switch (god){
            case "apollo":
                layout.setStyle("-fx-background-image: url('/img/gods/Apollo_desc.png')");
                break;
            case "hera":
                layout.setStyle("-fx-background-image: url('/img/gods/Hera_desc.png')");
                break;
            case "prometheus":
                layout.setStyle("-fx-background-image: url('/img/gods/Prometheus_desc.png')");
                break;
            case "artemis":
                layout.setStyle("-fx-background-image: url('/img/gods/Artemis_desc.png')");
                break;
            case "triton":
                layout.setStyle("-fx-background-image: url('/img/gods/Triton_desc.png')");
                break;
            case "zeus":
                layout.setStyle("-fx-background-image: url('/img/gods/Zeus_desc.png')");
                break;
            case "minotaur":
                layout.setStyle("-fx-background-image: url('/img/gods/Minotaur_desc.png')");
                break;
            case "demeter":
                layout.setStyle("-fx-background-image: url('/img/gods/Demeter_desc.png')");
                break;
            case "athena":
                layout.setStyle("-fx-background-image: url('/img/gods/Athena_desc.png')");
                break;
            case "pan":
                layout.setStyle("-fx-background-image: url('/img/gods/Pan_desc.png')");
                break;
            case "chronus":
                layout.setStyle("-fx-background-image: url('/img/gods/Chronus_desc.png')");
                break;
            case "hestia":
                layout.setStyle("-fx-background-image: url('/img/gods/Hestia_desc.png')");
                break;
            case "hephaestus":
                layout.setStyle("-fx-background-image: url('/img/gods/Hephaestus_desc.png')");
                break;
            case "atlas":
                layout.setStyle("-fx-background-image: url('/img/gods/Atlas_desc.png')");
                break;
        }
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
