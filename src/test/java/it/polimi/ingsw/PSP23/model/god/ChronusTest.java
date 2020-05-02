package it.polimi.ingsw.PSP23.model.god;

import it.polimi.ingsw.PSP23.model.Cell;
import it.polimi.ingsw.PSP23.model.Color;
import it.polimi.ingsw.PSP23.model.Status;
import it.polimi.ingsw.PSP23.model.Worker;
import org.junit.*;
import static org.junit.Assert.*;

public class ChronusTest {
    Chronus criceto;
    Worker hamtaro;
    Cell albero, foglie;

    @Before
    public void setUp() {
        criceto = new Chronus();
        albero = new Cell();
        foglie = new Cell();
        hamtaro = new Worker(albero,Color.WHITE);
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
        foglie.setCoord(1,1);
        albero.setCoord(1,2);
        albero.build(Status.BUILT);
        criceto.startTurn(false);
        assertEquals(0, criceto.move(foglie,hamtaro));
        for (int i = 0; i < 3; i++) {
            foglie.build(Status.BUILT);
        }
        assertTrue(criceto.checkWin(hamtaro));
        for(int i = 0; i < 5; i++) {
            assertFalse(criceto.checkWin(null));
            criceto.addCompletedTower();
        }
        assertTrue(criceto.checkWin(null));
    }

}
