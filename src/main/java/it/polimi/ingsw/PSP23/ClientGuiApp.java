package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.client.Client;
import it.polimi.ingsw.PSP23.GUI.Vars;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Cliet GUI
 */
public class ClientGuiApp extends Application{
    public static void main( String[] args ) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Santorini");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(ClientApp.class.getResourceAsStream("/img/246x0w.png")));
        primaryStage.show();
    }
}
