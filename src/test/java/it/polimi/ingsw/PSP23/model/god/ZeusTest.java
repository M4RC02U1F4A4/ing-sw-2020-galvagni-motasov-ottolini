package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class ZeusTest {
    Zeus god;
    Worker gianni;
    Cell base;

    @Before
    public void setUp() {
        god = new Zeus();
        base = new Cell();
        gianni = new Worker(base, Color.BLUE, 0);
    }

    @After
    public void tearDown() {
        god = null;
        base = null;
        gianni = null;
    }

    @Test
    public void build() {
        base.setCoord(1,1);
        god.startTurn(false);
        assertEquals(0,god.build(base, Status.BUILT,gianni));
    }
}
