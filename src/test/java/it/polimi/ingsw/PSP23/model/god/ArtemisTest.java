package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class ArtemisTest {
    Artemis artemide;
    Worker september;
    Cell uno, dos, tre;

    @Before
    public void setUp() {
        artemide = new Artemis();
        uno = new Cell();
        dos = new Cell();
        tre = new Cell();
        september = new Worker(uno, Color.WHITE);
    }

    @After
    public void tearDown() {
        artemide = null;
        september = null;
        uno = null;
        dos = null;
        tre = null;
    }

    @Test
    public void move() {
        uno.setCoord(1,1);
        dos.setCoord(1,2);
        tre.setCoord(2,2);
        artemide.startTurn(false);
        assertEquals(0, artemide.move(dos,september));
        assertEquals(0, artemide.move(tre,september));
        artemide.startTurn(false);
        assertEquals(0,artemide.move(dos,september));
        assertEquals(-4,artemide.move(tre,september));
    }
}
