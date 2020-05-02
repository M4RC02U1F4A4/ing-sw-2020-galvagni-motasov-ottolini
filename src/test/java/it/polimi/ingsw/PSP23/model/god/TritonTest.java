package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class TritonTest {
    Triton god;
    Worker pesciolino;
    Cell uno, due, tre, quattro, cinque;

    @Before
    public void setUp() {
        god = new Triton();
        uno = new Cell();
        due = new Cell();
        tre = new Cell();
        quattro = new Cell();
        cinque = new Cell();
        pesciolino = new Worker(uno, Color.WHITE);
    }

    @After
    public void tearDown() {
        god = null;
        pesciolino = null;
        uno = null;
        due = null;
        tre = null;
        quattro = null;
        cinque = null;
    }

    @Test
    public void move() {
        uno.setCoord(0,1);
        due.setCoord(0,2);
        tre.setCoord(0,3);
        quattro.setCoord(0,4);
        cinque.setCoord(1,3);
        // move inside the board
        god.startTurn(false);
        assertEquals(0, god.move(due,pesciolino));
        assertEquals(0, god.move(tre,pesciolino));
        assertEquals(0, god.move(cinque,pesciolino));
        assertEquals(-2, god.move(quattro,pesciolino));
        // build and try to move
        god.startTurn(false);
        assertEquals(0,god.move(due,pesciolino));
        assertEquals(0,god.move(tre,pesciolino));
        assertEquals(0, god.build(cinque, Status.BUILT,pesciolino));
        assertEquals(-2,god.move(quattro,pesciolino));
    }
}
