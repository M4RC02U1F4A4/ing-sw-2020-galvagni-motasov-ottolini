package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.client.Client;
import it.polimi.ingsw.PSP23.GUI.Main;
import it.polimi.ingsw.PSP23.GUI.Vars;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ClientGuiApp extends Application {

    public static void main(String[] args) {
        Vars vars = new Vars();
        launch(args);
        Client c=new Client(vars.ipServer,13245);
        System.out.println(vars.username);
        try {
            c.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Santorini");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
        primaryStage.show();
    }
}
