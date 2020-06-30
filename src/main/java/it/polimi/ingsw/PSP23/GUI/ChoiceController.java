package it.polimi.ingsw.PSP23.GUI;

import it.polimi.ingsw.PSP23.ClientApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller of the game selection window
 */
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
    private Button choiceNext;
    @FXML
    private Button choiceError;
    @FXML
    private CheckBox checkApollo;
    @FXML
    private CheckBox checkHera;
    @FXML
    private CheckBox checkPrometheus;
    @FXML
    private CheckBox checkArtemis;
    @FXML
    private CheckBox checkTriton;
    @FXML
    private CheckBox checkZeus;
    @FXML
    private CheckBox checkMinotaur;
    @FXML
    private CheckBox checkDemeter;
    @FXML
    private CheckBox checkAthena;
    @FXML
    private CheckBox checkPan;
    @FXML
    private CheckBox checkChronus;
    @FXML
    private CheckBox checkHestia;
    @FXML
    private CheckBox checkHephaestus;
    @FXML
    private CheckBox checkAtlas;

    /**
     * Choice confirmation button
     * Check the choice and send the 2/3 gods chosen to the server
     * Wait for my turn to choose my divinity
     */
    @FXML
    public void choiceNextAction(){
        Vars vars = new Vars();
        int check = 0;
        String gods = "";
        if(checkApollo.isSelected()){ check += 1; gods += "Apollo-";}
        if(checkHera.isSelected()){ check += 1; gods += "Hera-";}
        if(checkPrometheus.isSelected()){ check += 1; gods += "Prometheus-";}
        if(checkArtemis.isSelected()){ check += 1; gods += "Artemis-";}
        if(checkTriton.isSelected()){ check += 1; gods += "Triton-";}
        if(checkZeus.isSelected()){ check += 1; gods += "Zeus-";}
        if(checkMinotaur.isSelected()){ check += 1; gods += "Minotaur-";}
        if(checkDemeter.isSelected()){ check += 1; gods += "Demeter-";}
        if(checkAthena.isSelected()){ check += 1; gods += "Athena-";}
        if(checkPan.isSelected()){ check += 1; gods += "Pan-";}
        if(checkChronus.isSelected()){ check += 1; gods += "Chronus-";}
        if(checkHestia.isSelected()){ check += 1; gods += "Hestia-";}
        if(checkHephaestus.isSelected()){ check += 1; gods += "Hephaestus-";}
        if(checkAtlas.isSelected()){ check += 1; gods += "Atlas-";}
        if(Vars.numPlayer != check)choiceError.setVisible(true);
        else if(check == 2){
            gods = gods.substring(0, gods.length() - 1);
            String[] parts = gods.split("-");
            Vars.god1 = parts[0];
            Vars.god2 = parts[1];
            String godsToSend = "SELECT_GODS:" + Vars.god1 + "," + Vars.god2;
            Vars.magicWrite.println(godsToSend);
            Vars.magicWrite.flush();
            Stage stage = (Stage) choiceError.getScene().getWindow();
            stage.close();
            while( !(Vars.serverMsg.contains("CHOOSE_GOD")) && Vars.gameStatus == 1) {try{Thread.sleep(1000);} catch (InterruptedException e){}}
            if(Vars.gameStatus == 0){
                Alert timeout = new Alert(Alert.AlertType.ERROR);
                timeout.setHeaderText("Network error");
                timeout.setOnCloseRequest(e -> {
                    Platform.exit();
                    System.exit(0);
                });
                timeout.show();
            }
            else if(Vars.serverMsg.contains("CHOOSE_GOD")){
                try {
                    Parent rootChoice3 = FXMLLoader.load(getClass().getResource("/choice3.fxml"));
                    Stage choice3 = new Stage();
                    choice3.setTitle("Santorini - " + Vars.username);
                    choice3.setScene(new Scene(rootChoice3));
                    choice3.setResizable(false);
                    choice3.getIcons().add(new Image(ClientApp.class.getResourceAsStream("/img/246x0w.png")));
                    choice3.setOnCloseRequest(e -> {
                        Platform.exit();
                        System.exit(0);
                    });
                    choice3.show();
                } catch (IOException e) { e.printStackTrace(); }
            }
        }
        else if(check == 3){
            gods = gods.substring(0, gods.length() - 1);
            String[] parts = gods.split("-");
            Vars.god1 = parts[0];
            Vars.god2 = parts[1];
            Vars.god3 = parts[2];
            String godsToSend = "SELECT_GODS:" + Vars.god1 + "," + Vars.god2 + "," + Vars.god3;
            Vars.magicWrite.println(godsToSend);
            Vars.magicWrite.flush();
            Stage stage = (Stage) choiceError.getScene().getWindow();
            stage.close();
            while( !(Vars.serverMsg.contains("CHOOSE_GOD")) && Vars.gameStatus == 1) {try{Thread.sleep(1000);} catch (InterruptedException e){}}
             if(Vars.gameStatus == 0){
                Alert timeout = new Alert(Alert.AlertType.ERROR);
                timeout.setHeaderText("Network error");
                timeout.setOnCloseRequest(e -> {
                    Platform.exit();
                    System.exit(0);
                });
                timeout.show();
            }
            else if(Vars.serverMsg.contains("CHOOSE_GOD")) {
                try {
                    Parent rootChoice3 = FXMLLoader.load(getClass().getResource("/choice3.fxml"));
                    Stage choice3 = new Stage();
                    choice3.setTitle("Santorini - " + Vars.username);
                    choice3.setScene(new Scene(rootChoice3));
                    choice3.setResizable(false);
                    choice3.getIcons().add(new Image(ClientApp.class.getResourceAsStream("/img/246x0w.png")));
                    choice3.setOnCloseRequest(e -> {
                        Platform.exit();
                        System.exit(0);
                    });
                    choice3.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Button for disable the error
     */
    @FXML
    public void choiceErrorAction(){choiceError.setVisible(false);}

    /**
     * Open the description of the god
     */
    @FXML
    public void apolloAction(){description("apollo");}
    /**
     * Open the description of the god
     */
    @FXML
    public void heraAction(){description("hera");}
    /**
     * Open the description of the god
     */
    @FXML
    public void prometheusAction(){description("prometheus");}
    /**
     * Open the description of the god
     */
    @FXML
    public void artemisAction(){description("artemis");}
    /**
     * Open the description of the god
     */
    @FXML
    public void tritonAction(){description("triton");}
    /**
     * Open the description of the god
     */
    @FXML
    public void zeusAction(){description("zeus");}
    /**
     * Open the description of the god
     */
    @FXML
    public void minotaurAction(){description("minotaur");}
    /**
     * Open the description of the god
     */
    @FXML
    public void demeterAction(){description("demeter");}
    /**
     * Open the description of the god
     */
    @FXML
    public void athenaAction(){description("athena");}
    /**
     * Open the description of the god
     */
    @FXML
    public void panAction(){description("pan");}
    /**
     * Open the description of the god
     */
    @FXML
    public void chronusAction(){description("chronus");}
    /**
     * Open the description of the god
     */
    @FXML
    public void hestiaAction(){description("hestia");}
    /**
     * Open the description of the god
     */
    @FXML
    public void hephaestusAction(){description("hephaestus");}
    /**
     * Open the description of the god
     */
    @FXML
    public void atlasAction(){description("atlas");}

    public void description(String god){
        Vars.godChoice = god;
        try{
            Parent godDesc = FXMLLoader.load(getClass().getResource("/godDesc.fxml"));
            Stage godDescchoice = new Stage();
            godDescchoice.setTitle("Santorini - " + Vars.username);
            godDescchoice.setScene(new Scene(godDesc));
            godDescchoice.initModality(Modality.APPLICATION_MODAL);
            godDescchoice.setResizable(false);
            godDescchoice.getIcons().add(new Image(ClientApp.class.getResourceAsStream("/img/246x0w.png")));
            godDescchoice.show();
        } catch (IOException e) {e.printStackTrace();}
    }

}
