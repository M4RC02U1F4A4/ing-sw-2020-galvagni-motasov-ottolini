package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ChronusTest {
    Chronus criceto;
    Worker hamtaro;
    Cell albero, foglie;
    Map map;

    @Before
    public void setUp() {
        criceto = new Chronus();
        albero = new Cell();
        foglie = new Cell();
        hamtaro = new Worker(albero,Color.WHITE, 0);
    }

    @After
    public void tearDown() {
        criceto = null;
        albero = null;
        foglie = null;
        hamtaro = null;
    }

    @Test
    public void checkWin() {
        int i = 0;
        foglie.setCoord(1,1);
        albero.setCoord(1,2);
        albero.build(Status.BUILT);
        criceto.startTurn(false);
        assertEquals(0, criceto.move(foglie,hamtaro,map));
        for(int j = 0; j < 3; j++) {
            foglie.build(Status.BUILT);
        }
        assertTrue(criceto.checkWin(hamtaro, i));
        while(i < 5) {
            assertFalse(criceto.checkWin(null, i));
            i++;
        }
        assertTrue(criceto.checkWin(null, i));
    }

}
