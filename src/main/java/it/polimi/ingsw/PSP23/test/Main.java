package it.polimi.ingsw.PSP23.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Santorini");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(732);
        primaryStage.setMaxWidth(732);
        primaryStage.setMinHeight(760);
        primaryStage.setMaxHeight(760);
        //Disable full screen
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
        primaryStage.show();
    }

}
