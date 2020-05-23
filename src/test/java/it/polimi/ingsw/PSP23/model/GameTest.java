package it.polimi.ingsw.PSP23.model;

import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    Game hunger;

    @Before
    public void setUp() {
        hunger = new Game(2);
    }

    @After
    public void tearDown() {
        hunger = null;
    }

    @Test
    public void inout() {
        hunger.getMap();
        hunger.getCurrentPlayerNum();
        hunger.getGodList();
        hunger.getAllGodList();
        hunger.getPhase();
        hunger.getPhase();
    }

    @Test
    public void performemove() {
        //hunger.performeMove();
    }

    @Test
    public void gameprep() {
        hunger.addPlayer("Gianni", "1.1.1.1");
        hunger.godChoose("Atlas", "Athena", "Hera");
        hunger.setGod("Hera");
    }
}
