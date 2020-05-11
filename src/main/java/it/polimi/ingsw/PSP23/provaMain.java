package it.polimi.ingsw.PSP23;

//import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.model.god.Apollo;
import it.polimi.ingsw.PSP23.model.god.Atlas;
import it.polimi.ingsw.PSP23.server.SocketClientConnection;
import it.polimi.ingsw.PSP23.view.RemoteView;
import it.polimi.ingsw.PSP23.view.View;

import java.util.Arrays;

public class provaMain {
    public static void main(String[] args) {
        Map m=new Map();
        Game g=new Game();
        Player ivan=new Player("ivan","localhost");
        Player lapo=new Player("lapo","localhost");
        g.addPlayer(ivan);
        g.addPlayer(lapo);
        System.out.println(g.getPlayer(0).getPlayerNumber());
        System.out.println(g.getPlayer(1).getPlayerNumber());
        g.chooseRandomGods();
        System.out.println(Arrays.toString(g.getChosenGods().toArray()));
        g.addGod(ivan, "Zeus");
        g.addGod(lapo, "Apollo");
        System.out.println(ivan.getGod().name());
        System.out.println(lapo.getGod().name());
        Worker[] workers = new Worker[2];
        workers[0]=new Worker(g.getMap().getCell(0,0),Color.BLUE);
        workers[1]=new Worker(g.getMap().getCell(0,1),Color.BLUE);
        ivan.setWorkers(workers);
        g.getMap().drawMap();

        //God dio=new God();

        ivan.getGod().startTurn(false);
        //g.performeMove(1,1,ivan,Action.MOVE, 1);

        //ivan.getGod().move(g.getMap().getCell(1,1),ivan.getWorkerByNumber(0),g.getMap());
        g.performeMove(1, 1, ivan, Action.MOVE,1);
        //PlayerMove move=new PlayerMove(ivan, null, 1,1,Action.BUILD,1);
        //g.performeMove(move.getX(), move.getY(), move.getPlayer(), Action.MOVE, move.getnWorker());
        g.getMap().drawMap();

    }
}
