package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;

import java.nio.Buffer;

import static org.junit.Assert.*;

public class PanTest {
    Pan god;
    Worker gianni;
    Cell base, zero, one;
    Map map;

    @Before
    public void setUp() {
        god = new Pan();
        base = new Cell();
        zero = new Cell();
        one = new Cell();
        gianni = new Worker(base, Color.BLUE, 0);
    }

    @After
    public void tearDown() {
        god = null;
        base = null;
        zero = null;
        one = null;
        gianni = null;
    }

    @Test
    public void checkwin1() {
        base.setCoord(0,1);
        one.setCoord(1,0);
        zero.setCoord(1,1);
        //from 2 to 0
        base.build(Status.BUILT);
        base.build(Status.BUILT);
        one.build(Status.BUILT);
        god.startTurn(false);
        god.move(zero, gianni,map);
        assertTrue(god.checkWin(gianni, 0, false));
        //from 3 to 0
        god.startTurn(false);
        god.move(one,gianni,map);
        god.startTurn(false);
        god.move(base,gianni,map);
        base.build(Status.BUILT);
        god.startTurn(false);
        god.move(zero,gianni,map);
        assertTrue(god.checkWin(gianni, 15, false));
        //from 3 to 1
        god.startTurn(false);
        god.move(one,gianni,map);
        zero.build(Status.BUILT);
        zero.build(Status.BUILT);
        god.startTurn(false);
        god.move(zero,gianni,map);
        god.startTurn(false);
        god.move(base,gianni,map);
        god.startTurn(false);
        god.move(one,gianni,map);
        assertTrue(god.checkWin(gianni, 2, false));
        //from 1 to 2
        god.startTurn(false);
        god.move(zero,gianni,map);
        assertFalse(god.checkWin(gianni, -1, false));
        //from 2 to 3 border
        god.startTurn(false);
        god.move(base,gianni,map);
        assertFalse(god.checkWin(gianni, 9, true));
        //from 3 to 1 border
        god.startTurn(false);
        god.move(one,gianni,map);
        assertFalse(god.checkWin(gianni, 5, true));
    }
}
