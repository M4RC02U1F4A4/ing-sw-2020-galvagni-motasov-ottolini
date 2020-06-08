package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Apollo;
import it.polimi.ingsw.PSP23.model.god.Minotaur;
import it.polimi.ingsw.PSP23.model.god.Zeus;
import org.junit.*;

import static org.junit.Assert.*;

public class GodTest {
    God rjdio;
    Zeus saetta;
    Apollo apelle;
    Minotaur toro;
    Cell ulare, perfect, banana;
    Cell uno, due, tre, qua, cin, sei, set, ott, nov;
    Worker murathor, opelatole_ecologico, pedone1, pedone2, pedone3;
    Map map;

    @Before
    public void setUp() {
        ulare = new Cell();
        banana = new Cell();
        perfect = new Cell();
        rjdio = new God();
        saetta = new Zeus();
        apelle = new Apollo();
        toro = new Minotaur();
        map = new Map();
        murathor = new Worker(ulare, Color.RED, 0);
        opelatole_ecologico = new Worker(banana, Color.BLUE, 1);
        uno = map.getCell(0,0);
        due = map.getCell(0,1);
        tre = map.getCell(0,2);
        qua = map.getCell(1,0);
        cin = map.getCell(1,1);
        sei = map.getCell(1,2);
        set = map.getCell(2,0);
        ott = map.getCell(2,1);
        nov = map.getCell(2,2);
    }

    @After
    public void tearDown() {
        ulare = null;
        banana = null;
        perfect = null;
        rjdio = null;
        murathor = null;
        opelatole_ecologico = null;
        pedone1 = null;
        pedone2 = null;
        pedone3 = null;
    }

    @Test
    public void inizialization() {
        assertFalse(rjdio.is_hera_in_game);
        assertEquals(rjdio.remains_builds, 0);
        assertEquals(rjdio.remains_moves, 0);
        assertEquals(rjdio.starting_z, -2);
        assertEquals(rjdio.getName(), "zioDelTuono");
        rjdio.HeraIsHere();
        assertTrue(rjdio.is_hera_in_game);
    }

    @Test
    public void startTurn() {
        rjdio.startTurn(false);
        assertFalse(rjdio.AthenaMovedUp());
        rjdio.startTurn(true);
        assertTrue(rjdio.AthenaMovedUp());
        assertEquals(rjdio.remains_moves,1);
        assertEquals(rjdio.remains_builds, 1);
        assertEquals(rjdio.starting_z, -1);
    }

    @Test
    public void move() {
        // not near
        rjdio.remains_moves = 1;
        ulare.setCoord(1,2);
        perfect.setCoord(3,2);
        assertEquals(-1, rjdio.move(perfect, murathor, map));
        //occupied
        rjdio.remains_moves = 1;
        banana.setCoord(2,2);
        assertEquals(-1, rjdio.move(banana, murathor, map));
        // no moves
        rjdio.remains_moves = 0;
        perfect.setCoord(2,2);
        assertEquals(-2, rjdio.move(perfect, murathor, map));
        // athena power up, "It's over, Anakin! I have the high ground!"
        rjdio.AthenaIsHere();
        rjdio.setUpTurn(1,0,true);
        perfect.build(Status.BUILT);
        assertEquals(-3, rjdio.move(perfect, murathor, map));
        // athena is no moar, testing going up
        rjdio.setUpTurn(2,0,false);
        assertEquals(0, rjdio.move(perfect, murathor, map));
        assertEquals(0, rjdio.move(ulare, murathor, map));
        // going up 2
        rjdio.remains_moves = 1;
        rjdio.setUpTurn(1,0,false);
        murathor.moveWorker(ulare);
        perfect.build(Status.BUILT);
        assertEquals(-1, rjdio.move(perfect, murathor, map));
        ulare.build(Status.BUILT);
        assertEquals(0, rjdio.move(perfect, murathor, map));
        //going down 2
        rjdio.remains_moves = 1;
        rjdio.setUpTurn(1,0,false);
        murathor.moveWorker(ulare);
        perfect.build(Status.BUILT);
        assertEquals(-1, rjdio.move(ulare, murathor, map));
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
        assertEquals(3, rjdio.build(ulare, Status.BUILT, murathor));
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
            assertFalse(rjdio.checkWin(murathor, 0));
        }
        ulare.build(Status.BUILT);
        assertTrue(rjdio.checkWin(murathor, 12));
        //hera power enabled
        rjdio.HeraIsHere();
        //testing borders
        int i = 0;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(i,j);
            assertFalse(rjdio.checkWin(murathor, 34));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(i,j);
            assertFalse(rjdio.checkWin(murathor, 5));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(j,i);
            assertFalse(rjdio.checkWin(murathor, 3));
        }
        i = 4;
        for (int j = 0; j < 5; j++) {
            ulare.setCoord(j,i);
            assertFalse(rjdio.checkWin(murathor, 0));
        }
        //testing center
        for (i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                ulare.setCoord(i, j);
                assertTrue(rjdio.checkWin(murathor, 0));
            }
        }
    }

    /* number = height, o = occupied, b= base(height zero). bottom left = 0,0
        test 1, result = athena_moved_up
    2       o       1
    2       b       2
    2       o       2
        test 2 result = true
    2       o       2
    2       b       2
    2       o       2
        test 3 result = athena_moved_up (apollo)
    2       o(1)    2
    2       b       2
    2       o       2
        test 4 result = athena_moved_up (mino)
            0
    2       o(1)    2
    2       b       2
    2       o       2
        test 5 result = true (mino)
            o
    2       o(1)    2
    2       b       2
    2       o       2
        test 6 result = false
    2       o(1)    2
    2       b       2
    2       o       2
        test 7 result = true
    c       o(1)    c
    c       b       c
    c       o       c
        test 8 result = false (zeus)
    c       o(1)    c
    c       b       c
    c       o       c
     */
    @Test
    public void testCheckLoss() {
        for (int i = 0; i < 2; i++) {
            uno.build(Status.BUILT);
            tre.build(Status.BUILT);
            qua.build(Status.BUILT);
            sei.build(Status.BUILT);
            set.build(Status.BUILT);
        }
        nov.build(Status.BUILT);
        apelle.AthenaIsHere();
        toro.AthenaIsHere();
        //test 1
        rjdio.startTurn(false);
        pedone1 = new Worker(cin, Color.BLUE, 0);
        pedone2 = new Worker(due, Color.WHITE, 0);
        pedone3 = new Worker(ott, Color.WHITE, 1);
        assertFalse(rjdio.checkLossMove(pedone1, map));
        rjdio.startTurn(true);
        assertTrue(rjdio.checkLossMove(pedone1, map));
        //test 2
        nov.build(Status.BUILT);
        assertTrue(rjdio.checkLossMove(pedone1, map));
        //test 3
        ott.build(Status.BUILT);
        due.build(Status.BUILT);
        apelle.startTurn(false);
        assertFalse(apelle.checkLossMove(pedone1, map));
        apelle.startTurn(true);
        assertTrue(apelle.checkLossMove(pedone1, map));
        //test 4
        toro.startTurn(false);
        assertFalse(toro.checkLossMove(pedone1, map));
        toro.startTurn(true);
        assertTrue(toro.checkLossMove(pedone1, map));
        //test 5
        banana = map.getCell(3,1);
        opelatole_ecologico = new Worker(banana, Color.WHITE, 2);
        assertTrue(toro.checkLossMove(pedone1, map));
        //test 6
        assertFalse(rjdio.checkLossBuild(pedone1, map));
        //test 7
        for (int i = 0; i < 2; i++) {
            uno.build(Status.BUILT);
            tre.build(Status.BUILT);
            qua.build(Status.BUILT);
            sei.build(Status.BUILT);
            set.build(Status.BUILT);
            nov.build(Status.BUILT);
        }
        assertTrue(rjdio.checkLossBuild(pedone1, map));
        //test 8
        assertFalse(saetta.checkLossBuild(pedone1, map));
    }
}
