package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.Player;

public class provaMain {
    public static void main(String[] args) {
        Controller c=new Controller(new Game());
        c.addPlayer(new Player("ivan","127.0.0.1"));
        c.addPlayer(new Player("lapo","127.0.0.1"));
    }
}
