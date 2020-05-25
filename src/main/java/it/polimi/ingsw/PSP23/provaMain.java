package it.polimi.ingsw.PSP23;


import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.god.Apollo;

public class provaMain {
    public static void main(String[] args) {
        Game g=new Game(2);
        g.addPlayer("ivan","localhost");
        g.addPlayer("lapo","localhost");
        System.out.println(g.getCurrentPlayerNum());
        g.godChoose("Apollo","Athena","Zeus");
        g.setGod("Apollo");



    }
}
