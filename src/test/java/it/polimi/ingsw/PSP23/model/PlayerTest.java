package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    Map map;
    Player player;
    Worker[] workerlist = new Worker[2];

    @Before
    public void setUp() {
        map = new Map();
        player = new Player("marco", "192.168.1.5");
    }

    @After
    public void tearDown() {
        player = null;
        map = null;
        workerlist = null;
    }

    @Test
    public void playerSetGet(){
        assertEquals(player.getName(), "marco");
        assertEquals(player.getIpAddress(), "192.168.1.5");
        player.setName("filippo");
        player.setIpAddress("192.168.1.6");
        assertEquals(player.getName(), "filippo");
        assertEquals(player.getIpAddress(), "192.168.1.6");
    }

    @Test
    public void playerWorker(){
        workerlist[0] = new Worker(map.getCell(1, 1), Color.BLUE);
        workerlist[1] = new Worker(map.getCell(4, 4), Color.BLUE);
        player.setWorkers(workerlist);
        assertEquals(player.getWorkerByNumber(0), workerlist[0]);
        assertEquals(player.getWorkerByNumber(1), workerlist[1]);
        assertNull(player.getWorkerByNumber(2));
        Worker workertemp = new Worker(map.getCell(3, 3), Color.RED);
        player.setWorkerByNumber(workertemp, 0);
        assertEquals(player.getWorkerByNumber(0), workertemp);
        Worker[] workerlistTemp = new Worker[2];
        workerlistTemp = player.getWorkers();
        assertEquals(workerlistTemp[0], workerlist[0]);
        assertEquals(workerlistTemp[1], workerlist[1]);
    }


}