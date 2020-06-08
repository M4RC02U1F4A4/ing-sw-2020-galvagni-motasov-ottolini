package it.polimi.ingsw.PSP23.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIchoice extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent rootChoice = FXMLLoader.load(getClass().getResource("/choice.fxml"));
        Stage choice = new Stage();
        choice.setTitle("Santorini");
        choice.setScene(new Scene(rootChoice));
        choice.setResizable(false);
        choice.getIcons().add(new Image(Main.class.getResourceAsStream("/img/246x0w.png")));
        choice.show();
    }
}
