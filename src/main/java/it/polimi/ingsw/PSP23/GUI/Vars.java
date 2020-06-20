package it.polimi.ingsw.PSP23.GUI;

import it.polimi.ingsw.PSP23.model.Map;
import javafx.stage.Stage;

import java.io.PrintWriter;

/**
 * Variables used for the GUI
 */
public class Vars {
    public static String username;
    public static String ipServer = "localhost";
    public static String myGod = "";
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
    //4 CHOOSE_WORKER
    public static int turnStatus = 0;
    public static Map map;
    public static boolean mapEdit = false;
    public static String currentPlayer = "---";
    public static int gameStatus = 1;
    public static int statusWinLose = -1;
    public static boolean commandNotValid = false;
}
