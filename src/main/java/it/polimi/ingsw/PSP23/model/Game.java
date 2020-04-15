package it.polimi.ingsw.PSP23.model;

public class Game {
    public static void main( String[] args ) {
        Map m=new Map();
        TurnManager turnManager=new TurnManager(0);
        m.printStatus();
        System.out.println( "Hello World!" );
        /*
        Player p=new Player("LAPO", "127.0.0.1");
        p.setGod(new Apollo());

        Worker w0=new Worker(m.getCell(0,0),Color.RED);
        Worker w1=new Worker(m.getCell(4,3),Color.RED);

        //m.getCell(0,0).setWorker();
        p.setWorkerByNumber(w0,0);
        p.setWorkerByNumber(w1,1);
        p.getGod().startTurn(17,17);
        p.getGod().build(m.getCell(1,1),Status.BUILT,w0);
        p.getGod().startTurn(17,17);
        p.getGod().build(m.getCell(0,1),Status.BUILT,w0);
        p.getGod().startTurn(17,17);
        p.getGod().move(m.getCell(0,1),w0);
        p.getGod().startTurn(17,17);
        p.getGod().build(m.getCell(0,0),Status.BUILT,w0);
        p.getGod().startTurn(17,17);
        p.getGod().build(m.getCell(0,0),Status.BUILT,w0);
        p.getGod().startTurn(17,17);
        p.getGod().build(m.getCell(0,0),Status.CUPOLA,w0);
*/

        m.printStatus();

    }
}
