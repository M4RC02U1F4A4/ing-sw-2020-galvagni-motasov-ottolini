package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class HeraTest {
    Hera ero;
    Worker gianni;
    Cell base, top;

    @Before
    public void setUp() {
        ero = new Hera();
        base = new Cell();
        top = new Cell();
        gianni = new Worker(base, Color.BLUE);
    }

    @After
    public void tearDown() {
        ero = null;
        base = null;
        top = null;
        gianni = null;
    }

    @Test
    public void checkWin() {
        for (int i = 0; i < 2; i++) {
            base.build(Status.BUILT);
            top.build(Status.BUILT);
        }
        top.build(Status.BUILT);
        ero.startTurn(false);
        assertFalse(ero.checkWin(gianni));
        assertEquals(0, ero.move(top, gianni));
        assertTrue(ero.checkWin(gianni));
    }
}
