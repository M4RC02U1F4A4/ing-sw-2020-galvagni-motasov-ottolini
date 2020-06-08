package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.GUI.Main;
import it.polimi.ingsw.PSP23.GUI.Vars;
import it.polimi.ingsw.PSP23.client.Client;
import it.polimi.ingsw.PSP23.client.ClientGUI;
import it.polimi.ingsw.PSP23.model.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ClientApp extends Application{

    public static void main( String[] args ) {
        System.out.println("1 - GUI");
        System.out.println("2 - CLI");
        Scanner stdin=new Scanner(System.in);
        String inputLine = stdin.nextLine();
        //GUI
        if(inputLine.equals("1")){
            launch(args);
            ClientGUI c=new ClientGUI(Vars.ipServer,13245);
            try {
                c.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //CLI
        else if(inputLine.equals("2")){
            //TODO: Add input for ip
            Client c=new Client("127.0.0.1",13245);
            try {
                c.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //ERROR
        else {
            System.out.println("ERROR!!");
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
