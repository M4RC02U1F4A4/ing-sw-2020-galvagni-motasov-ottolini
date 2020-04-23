package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        worker = new Worker(map.getCell(1, 1), Color.BLUE);
        assertEquals(worker.getCell(), map.getCell(1, 1));
        assertEquals(worker.getColor(), Color.BLUE);
        worker.setColor(Color.WHITE);
        assertEquals(worker.getColor(), Color.WHITE);
        assertEquals(worker.getPosX(), 1, 0);
        assertEquals(worker.getPosY(), 1, 0);
        assertEquals(worker.getPosZ(), 0, 0);
        worker.moveWorker(map.getCell(2, 2));
        assertEquals(worker.getPosX(), 2, 0);
        assertEquals(worker.getPosY(), 2, 0);
        assertEquals(worker.getPosZ(), 0, 0);
    }

}