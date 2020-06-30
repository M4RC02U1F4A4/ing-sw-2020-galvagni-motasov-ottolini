package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;
import static org.junit.Assert.*;

public class WorkerTest {
    Map map;
    Worker worker;

    @Before
    public void setUp() {
        map = new Map();
    }

    @After
    public void tearDown() {
        map = null;
    }

    @Test
    public void inizializationSetteregetter(){
        worker = new Worker(map.getCell(1, 1), Color.BLUE, 0);
        assertEquals(worker.getCell(), map.getCell(1, 1));
        assertEquals(Color.BLUE, worker.getColor());
        worker.setColor(Color.WHITE);
        assertEquals(Color.WHITE, worker.getColor());
        assertEquals(1, worker.getPosX());
        assertEquals(1, worker.getPosY());
        assertEquals(0, worker.getPosZ());
        worker.moveWorker(map.getCell(2, 2));
        assertEquals(2, worker.getPosX());
        assertEquals(2, worker.getPosY());
        assertEquals(0, worker.getPosZ());
        worker.setWorkerNumber(0);
    }

    @Test
    public void moveTest() {
        Cell uno = map.getCell(1,1);
        Cell due = map.getCell(2,2);
        worker = new Worker(uno,Color.WHITE, 0);
        assertEquals(uno, worker.getCell());
        assertEquals(worker, uno.getWorker());
        worker.moveWorker(due);
        assertNull(uno.getWorker());
        assertEquals(due, worker.getCell());
        assertEquals(worker, due.getWorker());
    }
}