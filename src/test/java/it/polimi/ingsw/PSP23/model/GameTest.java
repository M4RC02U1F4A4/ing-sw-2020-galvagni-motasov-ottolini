package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Apollo;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    Game hunger;

    @Before
    public void setUp() {
        hunger = new Game(2);
    }

    @After
    public void tearDown() {
        hunger = null;
    }

    @Test
    public void getterSetter() {
        hunger.getMap();
        hunger.getCurrentPlayerNum();
        hunger.getAllGodList();
        hunger.getPhase();
        hunger.getPhase();
        hunger.sendWin(null);
        hunger.sendLoss(null);
    }

    @Test
    public void setGods() {
        Game quindici, trenta, quarantacinque;
        hunger = new Game(3);
        quindici = new Game(3);
        trenta = new Game(3);
        quarantacinque = new Game(3);
        for (int i = 0; i < 3; i++) {
            hunger.addPlayer("Tumbleweed", "1.1.1.1");
            quindici.addPlayer("Tumbleweed", "1.1.1.1");
            trenta.addPlayer("Tumbleweed", "1.1.1.1");
            quarantacinque.addPlayer("Tumbleweed", "1.1.1.1");
        }
        quindici.godChoose("Apollo", "Artemis", "Athena");
        trenta.godChoose("Atlas", "Chronus", "Demeter");
        quarantacinque.godChoose("Hephaestus", "Hera", "Hestia");
        hunger.godChoose("Minotaur", "Pan", "Prometheus");
        assertEquals(0, quindici.setGod("Apollo"));
        assertEquals(0, quindici.setGod("Artemis"));
        assertEquals(0, quindici.setGod("Athena"));
        assertEquals(0, trenta.setGod("Atlas"));
        assertEquals(0, trenta.setGod("Chronus"));
        assertEquals(0, trenta.setGod("Demeter"));
        assertEquals(0, quarantacinque.setGod("Hephaestus"));
        assertEquals(0, quarantacinque.setGod("Hera"));
        assertEquals(0, quarantacinque.setGod("Hestia"));
        assertEquals(0, hunger.setGod("Minotaur"));
        assertEquals(0, hunger.setGod("Pan"));
        assertEquals(0, hunger.setGod("Prometheus"));
    }

    @Test
    public void gamePrep() {
        hunger.addPlayer("Gianni", "1.1.1.1");
        hunger.addPlayer("Vladimir", "11.11.11.11");
        assertEquals(-1, hunger.addPlayer("Dimitri", "111.111.111.111"));
        hunger.godChoose("Triton", "Zeus", "Hera");
        hunger.getGodList();
        assertEquals(-1, hunger.setGod("Hera"));
        assertEquals(0, hunger.setGod("Zeus"));
        assertEquals(0, hunger.setGod("Triton"));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 2, 2));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 3, 3));
        assertEquals(-1, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 2, 2));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 4, 4));
    }

    @Test
    public void gameOn() {
        this.gamePrep();
        // Triton turn (player 0)
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        assertEquals(-1, hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 5, 0, 0));
        hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 1, 0, 0);
        assertEquals(0, hunger.performeMove(Action.MOVE, Status.FREE, 3, 3, 4));
        assertEquals(0, hunger.performeMove(Action.MOVE, Status.FREE, 3, 2, 4));
        assertEquals(1, hunger.performeMove(Action.SKIP, Status.FREE, 4, 2, 0));
        hunger.performeMove(Action.BUILD, Status.FREE, 3, 1, 1);
        hunger.performeMove(Action.BUILD, Status.FREE, 3, 3, 3);
        assertEquals(1, hunger.getCurrentPlayerNum());
        // Zeus turn (player 1), worker 2 on the border
        assertEquals(0, hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 0, 0, 0));
        hunger.performeMove(Action.SKIP, Status.FREE, 4, 2, 0);
        assertEquals(0, hunger.performeMove(Action.MOVE, Status.FREE, 3, 2, 3));
        hunger.performeMove(Action.BUILD, Status.FREE, 3, 3, 3);
        assertEquals(0, hunger.getCurrentPlayerNum());
        hunger.getPhase();
    }
}
