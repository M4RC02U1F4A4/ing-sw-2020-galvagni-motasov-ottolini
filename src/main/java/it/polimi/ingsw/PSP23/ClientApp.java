package it.polimi.ingsw.PSP23;

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
 *  Client
 */
public class ClientApp{

    public static void main( String[] args ) {
        System.out.println("1 - GUI");
        System.out.println("2 - CLI");
        Scanner stdin=new Scanner(System.in);
        String inputLine = stdin.nextLine();
        //GUI
        if(inputLine.equals("1")){
            new Thread(() -> {
                javafx.application.Application.launch(ClientGuiApp.class);
            }).start();
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