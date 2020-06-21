package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.*;
import it.polimi.ingsw.PSP23.model.god.Apollo;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PlayerTest {
    Map map;
    Player player;

    @Before
    public void setUp() {
        map = new Map();
        player = new Player("marco", "192.168.1.5");
    }

    @After
    public void tearDown() {
        player = null;
        map = null;
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
        player.placeWorker(new Cell(0,0));
        player.placeWorker(new Cell(0,1));
        player.getWorkerByNumber(0);
        player.getWorkers();
    }


}
