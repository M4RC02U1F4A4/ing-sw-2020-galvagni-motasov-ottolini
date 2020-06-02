package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class HestiaTest {
    Hestia god;
    Worker gianni;
    Cell base, border, notborder;

    @Before
    public void setUp() {
        god = new Hestia();
        base = new Cell();
        border = new Cell();
        notborder = new Cell();
        gianni = new Worker(base, Color.BLUE, 0);
    }

    @After
    public void tearDown() {
        god = null;
        base = null;
        border = null;
        notborder = null;
        gianni = null;
    }

    @Test
    public void build() {
        border.setCoord(0,1);
        notborder.setCoord(1,1);
        base.setCoord(1,0);
        god.startTurn(false);
        assertEquals(0, god.build(border,Status.BUILT,gianni));
        assertEquals(-5, god.build(border,Status.BUILT,gianni));
        assertEquals(0, god.build(notborder,Status.BUILT,gianni));
    }
}
