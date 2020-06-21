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

    @Test
    public void setUp3() {
        hunger = new Game(3);
        hunger.addPlayer("Luigi", "1.1.1.1");
        hunger.addPlayer("Mario", "11.11.11.11");
        hunger.addPlayer("Toad", "111.111.111.111");
        hunger.godChoose("Triton", "Zeus", "Chronus");
        hunger.setGod("Zeus");
        hunger.setGod("Triton");
        hunger.setGod("Chronus");
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
        this.setUp3();
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
        assertEquals(Phase.CHOOSE_WORKER, hunger.getPhase());
        assertFalse(hunger.getMap().getCell(0,0).isOccupied());
    }
}
