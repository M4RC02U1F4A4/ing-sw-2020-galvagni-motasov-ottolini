package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Apollo;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    Game hunger;

    @Before
    public void setUp() {
        hunger = new Game(2);
        hunger.addPlayer("Luigi", "1.1.1.1");
        hunger.addPlayer("Mario", "11.11.11.11");
        hunger.godChoose("Triton", "Zeus", "gino");
        hunger.setGod("Zeus");
        hunger.setGod("Triton");
    }

    @After
    public void tearDown() {
        hunger = null;
    }

    public void setUpCustom(String primo, String secondo, String terzo, int numplayer) {
        hunger = new Game(numplayer);
        hunger.addPlayer("Luigi", "1.1.1.1");
        hunger.addPlayer("Mario", "11.11.11.11");
        if (3 == numplayer)
            hunger.addPlayer("Toad", "111.111.111.111");
        hunger.godChoose(primo, secondo, terzo);
        hunger.godsc();
        hunger.setGod(primo);
        hunger.godsc();
        hunger.setGod(secondo);
        hunger.godsc();
        if (3 == numplayer)
            hunger.setGod(terzo);
    }

    @Test
    public void getterSetter() {
        hunger.getMap();
        hunger.getCurrentPlayerNum();
        hunger.getAllGodList();
        hunger.getGodList();
        hunger.getPhase();
    }

    @Test
    public void gameOn() {
        assertEquals(-1, hunger.performeMove(Action.CHOOSE_GOD, Status.FREE, 3, 3, 4));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 2, 2));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 3, 3));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 4, 4));
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
        hunger.isPlayerTurn(hunger.getPlayer(hunger.getCurrentPlayerNum()));
        hunger.isPlayerTurn(hunger.getPlayer(hunger.getCurrentPlayerNum() + 1));
    }

    @Test
    public void lose() {
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 0));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 0));
        // Triton turn (player 0)
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        assertEquals(0, hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 0, 0, 0));
        assertEquals(Phase.BAD_NEWS, hunger.getPhase());
    }

    @Test
    public void removePlayer() {
        this.setUpCustom("Prometheus","Triton","Hestia", 3);
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 0));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 0));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 2, 2));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 3, 3));
        // Triton turn (player 0)
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        assertEquals(0, hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 0, 0, 0));
        assertEquals(Phase.BAD_NEWS, hunger.getPhase());
        hunger.removePlayer();
        assertEquals(0, hunger.getCurrentPlayerNum());
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        assertFalse(hunger.getMap().getCell(0,0).isOccupied());
    }

    @Test
    public void skip() {
        this.setUpCustom("Prometheus", "Triton", "Hestia", 3);
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 0));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 0, 1));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 1, 0));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 2, 2));
        assertEquals(0, hunger.performeMove(Action.PLACE_WORKER, Status.FREE, 0, 3, 3));
        //Hestia turn
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 1, 0, 0);
        assertEquals(0, hunger.performeMove(Action.MOVE, Status.FREE, 1, 2, 1));
        hunger.performeMove(Action.BUILD, Status.FREE, 1, 1, 2);
        hunger.performeMove(Action.SKIP, Status.FREE, 1, 0, 0);
        //Prometheus turn
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        hunger.performeMove(Action.SELECT_WORKER, Status.FREE, 1, 0, 0);
        hunger.performeMove(Action.SKIP, Status.FREE, 1, 0, 0);
        assertEquals(0, hunger.performeMove(Action.MOVE, Status.FREE, 1, 2, 3));
        hunger.performeMove(Action.BUILD, Status.FREE, 1, 3, 2);
    }

    @Test
    public void moreGods() {
        this.setUpCustom("Apollo","Artemis","Athena", 3);
        this.setUpCustom("Atlas","Demeter","Hephaestus", 3);
        this.setUpCustom("Hera","Hestia","Minotaur", 3);
        this.setUpCustom("Pan","Prometheus","Chronus", 2);
    }
}
