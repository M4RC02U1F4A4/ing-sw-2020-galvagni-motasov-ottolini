package it.polimi.ingsw.PSP23.GUI;

import javafx.stage.Stage;

import java.io.PrintWriter;

public class Vars {
    public static String username;
    public static String ipServer = "localhost";
    public static String player1Name = "";
    public static String player2Name = "";
    public static String player3Name = "";
    public static String player1God = "";
    public static String player2God = "";
    public static String player3God = "";
    public static int numPlayer;
    public static String god1 = "";
    public static String god2 = "";
    public static String god3 = "";
    public static boolean god1Used = false;
    public static boolean god2Used = false;
    public static boolean god3Used = false;
    public static PrintWriter magicWrite;
    public static String serverMsg = "";
    //0 Not your turn
    //1 PLACE_WORKER
    //2 MOVE
    //3 BUILD
    public static int turnStatus = 0;
}
