package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;

import java.nio.Buffer;

import static org.junit.Assert.*;

public class PanTest {
    Pan god;
    Worker gianni;
    Cell base, zero, one;

    @Before
    public void setUp() {
        god = new Pan();
        base = new Cell();
        zero = new Cell();
        one = new Cell();
        gianni = new Worker(base, Color.BLUE);
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
        god.move(zero, gianni);
        assertTrue(god.checkWin(gianni));
        //from 3 to 0
        god.startTurn(false);
        god.move(one,gianni);
        god.startTurn(false);
        god.move(base,gianni);
        base.build(Status.BUILT);
        god.startTurn(false);
        god.move(zero,gianni);
        assertTrue(god.checkWin(gianni));
        //from 3 to 1
        god.startTurn(false);
        god.move(one,gianni);
        zero.build(Status.BUILT);
        zero.build(Status.BUILT);
        god.startTurn(false);
        god.move(zero,gianni);
        god.startTurn(false);
        god.move(base,gianni);
        god.startTurn(false);
        god.move(one,gianni);
        assertTrue(god.checkWin(gianni));
        //from 1 to 2
        god.startTurn(false);
        god.move(zero,gianni);
        assertFalse(god.checkWin(gianni));
        //from 2 to 3 border
        god.HeraIsHere();
        god.startTurn(false);
        god.move(base,gianni);
        assertFalse(god.checkWin(gianni));
        //from 3 to 1 border
        god.startTurn(false);
        god.move(one,gianni);
        assertFalse(god.checkWin(gianni));
    }
}
