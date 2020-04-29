package it.polimi.ingsw.PSP23.model;

import org.junit.*;
import static org.junit.Assert.*;

public class GodTest {
    God rjdio;
    Cell ulare, perfect, banana;
    Worker murathor, opelatole_ecologico;

    @Before
    public void setUp() {
        ulare = new Cell();
        banana = new Cell();
        perfect = new Cell();
        rjdio = new God();
        murathor = new Worker(ulare, Color.RED);
        opelatole_ecologico = new Worker(banana, Color.BLUE);
    }

    @After
    public void tearDown() {
        ulare = null;
        banana = null;
        perfect = null;
        rjdio = null;
        murathor = null;
        opelatole_ecologico = null;
    }

    @Test
    public void inizialization() {
        assertFalse(rjdio.is_hera_in_game);
        assertEquals(rjdio.remains_builds, 0);
        assertEquals(rjdio.remains_moves, 0);
        assertEquals(rjdio.starting_z, -2);
        assertEquals(rjdio.name(), "zioDelTuono");
    }

    @Test
    public void startTurn() {
        rjdio.startTurn(true);
        assertTrue(rjdio.AthenaMovedUp());
        assertEquals(rjdio.remains_moves,1);
        assertEquals(rjdio.remains_builds, 1);
        assertEquals(rjdio.starting_z, -1);
    }

    @Test
    public void move() {
        // not near
        murathor.moveWorker(ulare);
        rjdio.remains_moves = 1;
        ulare.setCoord(1,2);
        perfect.setCoord(3,2);
        assertEquals(-1, rjdio.move(perfect, murathor));
        //occupied
        rjdio.remains_moves = 1;
        banana.setCoord(2,2);
        assertEquals(-1, rjdio.move(banana, murathor));
        // no moves
        rjdio.remains_moves = 0;
        perfect.setCoord(2,2);
        assertEquals(-2, rjdio.move(perfect, murathor));
        // athena power up, "It's over, Anakin! I have the high ground!"
        rjdio.AthenaIsHere();
        rjdio.setUpTurn(1,0,true);
        perfect.build(Status.BUILT);
        assertEquals(-3, rjdio.move(perfect, murathor));
        // athena is no moar, testing going up
        rjdio.setUpTurn(2,0,false);
        assertEquals(0, rjdio.move(perfect, murathor));
        assertEquals(0, rjdio.move(ulare, murathor));
        // going up 2
        rjdio.remains_moves = 1;
        rjdio.setUpTurn(1,0,false);
        murathor.moveWorker(ulare);
        perfect.build(Status.BUILT);
        assertEquals(-1, rjdio.move(perfect, murathor));
        ulare.build(Status.BUILT);
        assertEquals(0, rjdio.move(perfect, murathor));
        //going down 2
        rjdio.remains_moves = 1;
        rjdio.setUpTurn(1,0,false);
        murathor.moveWorker(ulare);
        perfect.build(Status.BUILT);
        assertEquals(-1, rjdio.move(ulare, murathor));
    }

    @Test
    public void build() {
        murathor.moveWorker(perfect);
        perfect.setCoord(1,1);
        banana.setCoord(2,1);
        ulare.setCoord(1,2);
        // no build avaiable
        rjdio.remains_moves = 2;
        rjdio.remains_builds = 0;
        assertEquals(-2, rjdio.build(ulare, Status.BUILT, murathor));
        assertEquals(0, rjdio.remains_moves);
        // not near
        rjdio.remains_builds = 1;
        ulare.setCoord(3,1);
        assertEquals(-1, rjdio.build(ulare, Status.BUILT, murathor));
        // tring to build under feet
        rjdio.remains_builds = 1;
        assertEquals(-1, rjdio.build(perfect, Status.BUILT, murathor));
        // built space
        rjdio.remains_builds = 1;
        ulare.setCoord(1,2);
        assertEquals(0, rjdio.build(ulare, Status.BUILT, murathor));
        // built cupola
        ulare.build(Status.BUILT);
        ulare.build(Status.BUILT);
        rjdio.remains_builds = 1;
        assertEquals(0, rjdio.build(ulare, Status.BUILT, murathor));
        assertEquals(Status.CUPOLA, ulare.levelStatus(3));
        // building cupola via attribute
        rjdio.remains_builds = 1;
        assertEquals(0, rjdio.build(banana, Status.CUPOLA, murathor));
        assertEquals(Status.CUPOLA, ulare.levelStatus(3));
    }

    @Test
    public void testCheckWin() {
        for (int i = 0; i < 2; i++) {
            rjdio.starting_z = 1;
            ulare.build(Status.BUILT);
            murathor.moveWorker(ulare);
            assertFalse(rjdio.checkWin(murathor));
        }
        ulare.build(Status.BUILT);
        assertTrue(rjdio.checkWin(murathor));
        //hera power enabled
        rjdio.HeraIsHere();
        //testing borders
        int i = 0;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(i,j);
            assertFalse(rjdio.checkWin(murathor));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(i,j);
            assertFalse(rjdio.checkWin(murathor));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(j,i);
            assertFalse(rjdio.checkWin(murathor));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(j,i);
            assertFalse(rjdio.checkWin(murathor));
        }
        //testing center
        for (i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                ulare.setCoord(i, j);
                assertTrue(rjdio.checkWin(murathor));
            }
        }
    }
}
