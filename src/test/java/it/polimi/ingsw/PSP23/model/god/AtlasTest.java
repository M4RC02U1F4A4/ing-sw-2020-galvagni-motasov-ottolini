package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class AtlasTest {
    Atlas mappamondo;
    Worker gianni;
    Cell polo, nord;

    @Before
    public void setUp() {
        mappamondo = new Atlas();
        polo = new Cell();
        nord = new Cell();
        gianni = new Worker(polo, Color.WHITE, 0);
    }

    @After
    public void tearDown() {
        mappamondo = null;
        polo = null;
        nord = null;
        gianni = null;
    }

    @Test
    public void build() {
        polo.setCoord(4,4);
        nord.setCoord(4,3);
        mappamondo.startTurn(false);
        assertEquals(-1, mappamondo.build(polo, Status.CUPOLA, gianni));
        assertEquals(0, mappamondo.build(nord, Status.CUPOLA, gianni));
        assertEquals(Status.CUPOLA, nord.levelStatus(0));
    }
}
