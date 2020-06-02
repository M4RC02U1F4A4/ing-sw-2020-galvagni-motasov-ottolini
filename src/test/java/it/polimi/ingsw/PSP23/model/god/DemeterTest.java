package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class DemeterTest {
    Demeter persefone;
    Worker melograno;
    Cell abra, kadabra, alakazam;

    @Before
    public void setUp() {
        persefone = new Demeter();
        abra = new Cell();
        kadabra = new Cell();
        alakazam = new Cell();
        melograno = new Worker(abra, Color.RED, 0);
    }

    @After
    public void tearDown() {
        persefone = null;
        abra = null;
        kadabra = null;
        alakazam = null;
        melograno = null;
    }

    @Test
    public void build() {
        abra.setCoord(1,1);
        kadabra.setCoord(1,2);
        alakazam.setCoord(2,2);
        persefone.startTurn(false);
        assertEquals(0, persefone.build(kadabra, Status.BUILT, melograno));
        assertEquals(-3, persefone.build(kadabra, Status.BUILT, melograno));
        assertEquals(0, persefone.build(alakazam, Status.BUILT, melograno));
    }
}
