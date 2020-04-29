package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class AthenaTest {
    Athena nike;
    Worker gianni;
    Cell little, big;

    @Before
    public void setUp() {
        nike = new Athena();
        little = new Cell();
        big = new Cell();
        gianni = new Worker(big, Color.WHITE);
    }

    @After
    public void tearDown() {
        nike = null;
        little = null;
        big = null;
        gianni = null;
    }

    @Test
    public void move() {
        nike.startTurn(true);
        assertFalse(nike.CheckMovedUp());
        nike.startTurn(false);
        assertFalse(nike.CheckMovedUp());
        assertEquals(0, nike.move(little, gianni));
        nike.startTurn(false);
        big.build(Status.BUILT);
        big.setCoord(1,1);
        little.setCoord(2,2);
        assertEquals(0, nike.move(big, gianni));
        assertTrue(nike.CheckMovedUp());
    }
}
