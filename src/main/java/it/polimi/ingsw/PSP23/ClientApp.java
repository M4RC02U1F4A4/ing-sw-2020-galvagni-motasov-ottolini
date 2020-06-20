package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.GUI.Main;
import it.polimi.ingsw.PSP23.client.Client;
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
            }
        //CLI
        else if(inputLine.equals("2")){
            try {
                String ip="127.0.0.1";
                System.out.println("Inserisci l'ip del server: ");
                //BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
                ip=stdin.nextLine();
                Client c=new Client(ip,13245);
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
/*
*
public class ClientApp
{
    public static void main(String[] args){
        System.out.println("Inserisci l'ip del server: ");
        BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
        try {
            String ip=keyboard.readLine();
            keyboard.close();
            Client client = new Client(ip, 12345);

            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/