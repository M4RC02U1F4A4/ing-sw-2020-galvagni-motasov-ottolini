package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class PrometheusTest {
    Prometheus god;
    Worker fuoco;
    Cell uno, due, tre;

    @Before
    public void setUp() {
        god = new Prometheus();
        uno = new Cell();
        due = new Cell();
        tre = new Cell();
        fuoco = new Worker(uno, Color.WHITE);
    }

    @After
    public void tearDown() {
        god = null;
        fuoco = null;
        uno = null;
        due = null;
        tre = null;
    }

    @Test
    public void move() {
        uno.setCoord(0,1);
        due.setCoord(1,0);
        tre.setCoord(1,1);
        // build, move and build
        god.startTurn(false);
        assertEquals(0, god.build(due,Status.BUILT,fuoco));
        assertEquals(0, god.move(tre,fuoco));
        assertEquals(0, god.build(uno,Status.BUILT,fuoco));
        // build, move up and try to build
        god.startTurn(false);
        assertEquals(0, god.build(uno,Status.BUILT,fuoco));
        assertEquals(0, god.move(due,fuoco));
        assertEquals(-2, god.build(tre,Status.BUILT,fuoco));
    }
}
