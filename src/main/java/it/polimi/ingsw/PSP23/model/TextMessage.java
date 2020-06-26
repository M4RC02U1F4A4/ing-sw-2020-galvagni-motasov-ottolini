package it.polimi.ingsw.PSP23.model;

import java.util.Arrays;

public class TextMessage {
    public static String timeout="Partita terminata: timeout";
    public static String chooseYourGod="Scegli un dio tra quelli disponibili:";
    public static String getChooseYourGodSyntax="Sintassi del comando: \\nCHOOSE_GOD:<god>";
    public static String waitForYourTurn="Attendi il tuo turno";
    public static String wrongComand="Comando non valido! Riprova";
    public static String wrongGod="Parametro divinita' non valido: riprova";
    public static String startingGame="STARTING THE GAME";
    public static String placeWorker="Piazza un worker sulla mappa \nSintassi del comando:\nPLACE_WORKER:<x>,<y>";
    public static String chooseWorker="Scegli il worker per questo turno:\nSintassi del comando\nCHOOSE_WORKER:<nWorker>";
    public static String whereToMove="Scegli dove muoverti:\nSintassi del comando:\nMOVE:<x>,<y>";
    public static String whereToBuild="Scegli dove costruire:\nSintassi del comando:\nBUILD:<x>,<y>,<blocco o cupola>";
    public static String winMessage="WIN";
    public static String loseMessage="LOSE";
    public static String wrongTurn="NON È IL TUO TURNO!";
    public static String greetings2s="Benvenuto nella lobby a 2 giocatori";
    public static String yourTurn="e' il tuo turno";
    public static String chooseGods2s="Scegli 2 dei tra quelli disponibili: "+ Arrays.toString(God.getAllGods().toArray())+"\nSintassi del comando: SELECT_GODS:<god1>,<god2> ";
    public static String getGreetings3s="Benvenuto nella lobby a 3 giocatori";
    public static String chooseGods3s="Scegli 3 dei tra quelli disponibili: "+ Arrays.toString(God.getAllGods().toArray())+"\nSintassi del comando: SELECT_GODS:<god1>,<god2>,<god3> ";
    public static String wrongGodChosen="Divinita' non valida, riprova";
    public static String wrongComandWritten="Il comando inserito non è valido, riprova";
    public static String yourNamePls="Inserisci il tuo nome";
    public static String howMany="Inserisci il numero di giocatori";
}
