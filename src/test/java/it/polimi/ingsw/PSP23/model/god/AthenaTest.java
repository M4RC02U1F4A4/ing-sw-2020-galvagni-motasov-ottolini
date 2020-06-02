package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;
import static org.junit.Assert.*;

public class AthenaTest {
    Athena nike;
    Worker gianni;
    Cell little, big;
    Map map;

    @Before
    public void setUp() {
        nike = new Athena();
        little = new Cell();
        big = new Cell();
        gianni = new Worker(big, Color.WHITE, 0);
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
        assertEquals(0, nike.move(little, gianni,map));
        nike.startTurn(false);
        big.build(Status.BUILT);
        big.setCoord(1,1);
        little.setCoord(2,2);
        assertEquals(0, nike.move(big, gianni,map));
        assertTrue(nike.CheckMovedUp());
    }
}
