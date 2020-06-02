package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class MinotaurTest {
    Minotaur toro;
    Cell uno, due, tre, qua;
    Worker w, k, y;
    Map map;

    @Before
    public void setUp() {
        map = new Map();
        toro = new Minotaur();
        uno = map.getCell(1,1);
        due = map.getCell(2,2);
        tre = map.getCell(3,3);
        qua = map.getCell(0,0);
        w = new Worker(qua, Color.BLUE, 0);
        k = new Worker(due, Color.RED, 0);
        y = new Worker(tre, Color.WHITE, 0);
    }

    @After
    public void tearDown() {
        toro = null;
        uno = null;
        due = null;
        tre = null;
        qua = null;
        w = null;
        k = null;
        y = null;
    }

    @Test
    public void move() {
        // normal move
        toro.startTurn(false);
        assertEquals(0, toro.move(uno, w, map));
        // minotaur power tre occupied
        toro.startTurn(false);
        assertEquals(-1, toro.move(due, w, map));
        // minotaur power tre empty
        qua.setCoord(4,4);
        y.moveWorker(qua);
        assertEquals(0, toro.move(due, w, map));
        assertEquals(tre, k.getCell());
    }
}
