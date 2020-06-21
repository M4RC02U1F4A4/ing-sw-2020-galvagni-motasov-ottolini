package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HeraTest {
    Hera ero;
    Worker gianni;
    Cell base, top;
    Map map;

    @Before
    public void setUp() {
        ero = new Hera();
        base = new Cell();
        top = new Cell();
        gianni = new Worker(base, Color.BLUE, 0);
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
        assertFalse(ero.checkWin(gianni, 0, true));
        assertEquals(0, ero.move(top, gianni,map));
        assertTrue(ero.checkWin(gianni, 0, true));
    }
}
