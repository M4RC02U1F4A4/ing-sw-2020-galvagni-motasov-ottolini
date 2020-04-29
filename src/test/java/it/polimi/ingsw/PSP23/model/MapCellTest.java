package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.*;
import org.junit.*;

import static org.junit.Assert.*;

public class MapCellTest {
    Cell cell;
    Map map;


    @Before
    public void setUp() {
        cell = new Cell();
        map = new Map();
    }

    @After
    public void tearDown() {
        cell = null;
        map = null;
    }

    @Test
    public void inizialization(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                for(int k=0; k<4; k++){
                    assertEquals(map.getCell(i, j).levelStatus(k), Status.FREE);
                }
            }
        }
    }

    /**
     * Test map clone with one random cell modified to
     * make sure the map is cloned and not created
     */
    @Test
    public void cloneWithSemiRandomMap(){
        Map mappa = new Map();
        mappa.getCell(2, 2).build(Status.BUILT);
        mappa.getCell(2, 2).build(Status.BUILT);
        Map cloneMap = new Map();
        cloneMap = mappa.clone();
        assertEquals(cloneMap.getCell(2, 2).height(), 2, 0);
    }

    /**
     * Test build
     * Normal build
     * Try to build over a CUPOLA
     */
    @Test
    public void genericBuilt(){
        assertEquals(cell.height(), 0, 0);
        cell.build(Status.BUILT);
        assertEquals(cell.height(), 1, 0);
        cell.build(Status.BUILT);
        assertEquals(cell.height(), 2, 0);
        cell.build(Status.CUPOLA);
        assertEquals(cell.height(), 3, 0);
        cell.build(Status.BUILT);
        assertEquals(cell.height(), 3, 0);
    }

    /**
     * automatic CUPOLA construction
     */
    @Test
    public void automaticCupola(){
        cell.build(Status.BUILT);
        cell.build(Status.BUILT);
        cell.build(Status.BUILT);
        cell.build(Status.BUILT);
        assertEquals(cell.height(), 4, 0);
        assertEquals(cell.levelStatus(2), Status.BUILT);
        assertEquals(cell.levelStatus(3), Status.CUPOLA);
        assertEquals(cell.levelStatus(4), Status.NOT_AVAILABLE);
    }

    @Test
    public void cellWithWorker(){
        Worker worker = new Worker(map.getCell(1, 1), Color.BLUE);
        assertTrue(map.getCell(1, 1).isOccupied());
        assertFalse(map.getCell(2, 2).isOccupied());
        assertTrue(map.getCell(2, 2).isNear(worker, true));
        map.getCell(2, 2).build(Status.BUILT);
        map.getCell(2, 2).build(Status.BUILT);
        assertFalse(map.getCell(2, 2).isNear(worker, true));
        assertNull(map.getCell(6, 6));
        assertEquals(map.getCell(1, 1).getWorker(), worker);
        assertFalse(map.getCell(3, 1).isNear(worker, true));
        assertFalse(map.getCell(1, 3).isNear(worker, true));
    }

    /**
     * test debug print
     */
    @Test
    public void print(){
        map.getCell(1, 1).build(Status.BUILT);
        map.getCell(1, 1).build(Status.BUILT);
        map.getCell(1, 1).build(Status.BUILT);
        map.getCell(1, 1).build(Status.BUILT);
        System.out.println(map.getCell(1, 1).toString());
        map.printStatus();
    }

    @Test
    public void testCloneCell(){
        assertEquals(cell.height(), 0, 0);
        cell.build(Status.BUILT);
        assertEquals(cell.height(), 1, 0);
        Cell cell1 = cell.clone();
        assertEquals(cell1.height(), 1, 0);
        cell1 = null;
    }

}