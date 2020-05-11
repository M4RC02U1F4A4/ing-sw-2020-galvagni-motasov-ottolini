package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Player;

import java.util.Arrays;

public class provaMain {
    public static void main(String[] args) {
        Map m=new Map();
        Game g=new Game();
        g.addPlayer(new Player("ivan","localhost"));
        g.addPlayer(new Player("lapo","localhost"));
        System.out.println(g.getPlayer(0).getPlayerNumber());
        System.out.println(g.getPlayer(1).getPlayerNumber());
        g.chooseRandomGods();
        System.out.println(Arrays.toString(g.getChosenGods().toArray()));
    }
}
