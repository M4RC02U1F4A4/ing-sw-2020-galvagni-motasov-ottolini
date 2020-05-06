package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Map;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;


public class ApolloTest {
    Apollo apollo;
    Cell d, e, f, g;
    Worker w, k, y;
    Map map;

    @Before
    public void setUp() {
        apollo = new Apollo();
        d = new Cell();
        e = new Cell();
        f = new Cell();
        g = new Cell();
        w = new Worker(d, Color.RED);
        k = new Worker(e, Color.BLUE);
        y = new Worker(f, Color.RED);
    }

    @After
    public void tearDown() {
        apollo = null;
        d = null;
        e = null;
        f = null;
        g = null;
        w = null;
        k = null;
        y = null;
    }

    @Test
    public void Move() {
        d.setCoord(1,2);
        g.setCoord(1,1);
        // cell not occupied
        apollo.startTurn(false);
        assertEquals(0, apollo.move(g,w,map));
        // cell occupied by opponent's worker, success
        e.setCoord(2,1);
        apollo.startTurn(false);
        assertEquals(0, apollo.move(e,w,map));
        assertEquals(g, k.getCell());
        // cell occupied by opponent's worker, failed no moves left
        assertEquals(-2, apollo.move(g,w,map));
        // cell occupied by friendly worker
        f.setCoord(2,2);
        apollo.startTurn(false);
        assertEquals(-6, apollo.move(f,w,map));
    }
}
