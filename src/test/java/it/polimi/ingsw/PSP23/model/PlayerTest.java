package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.model.god.Apollo;
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
        assertEquals("marco", player.getName());
        assertEquals("192.168.1.5",player.getIpAddress());
        player.setName("filippo");
        player.setIpAddress("192.168.1.6");
        assertEquals("filippo", player.getName());
        assertEquals("192.168.1.6",player.getIpAddress());
        Apollo cedra = new Apollo();
        player.setGod(cedra);
        assertEquals(cedra,player.getGod());
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
        player.setColor(Color.WHITE);
        assertEquals(Color.WHITE,player.getColor());
        Worker Lurido = new Worker(map.getCell(3,3), Color.WHITE);
        assertNull(player.getWorkerByNumber(2));
        player.setWorkerByNumber(Lurido, 2);
    }


}
