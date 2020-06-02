package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class HephaestusTest {
    Hephaestus zoppo;
    Worker capobastone;
    Cell falce, martello, segaanastro;

    @Before
    public void setUp() {
        zoppo = new Hephaestus();
        falce = new Cell();
        martello = new Cell();
        segaanastro = new Cell();
        capobastone = new Worker(falce, Color.BLUE, 0);
    }

    @After
    public void tearDown() {
        zoppo = null;
        falce = null;
        martello = null;
        segaanastro = null;
        capobastone = null;
    }

    @Test
    public void build() {
        falce.setCoord(1,1);
        martello.setCoord(1,2);
        segaanastro.setCoord(2,2);
        zoppo.startTurn(false);
        assertEquals(0, zoppo.build(martello, Status.BUILT, capobastone));
        assertEquals(-4, zoppo.build(segaanastro, Status.BUILT, capobastone));
        assertEquals(1, zoppo.build(martello, Status.BUILT, capobastone));
    }
}
