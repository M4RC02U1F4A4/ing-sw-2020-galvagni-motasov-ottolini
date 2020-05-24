package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Apollo;
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
    public void inOut() {
        hunger.getMap();
        hunger.getCurrentPlayerNum();
        hunger.getGodList();
        hunger.getAllGodList();
        hunger.getPhase();
        hunger.getPhase();
    }

    @Test
    public void performeMove() {
        //hunger.performeMove();
    }

    @Test
    public void setGods() {
        Game quindici, trenta, quarantacinque;
        hunger = new Game(3);
        quindici = new Game(3);
        trenta = new Game(3);
        quarantacinque = new Game(3);
        for (int i = 0; i < 3; i++) {
            hunger.addPlayer("Tumbleweed", "1.1.1.1");
            quindici.addPlayer("Tumbleweed", "1.1.1.1");
            trenta.addPlayer("Tumbleweed", "1.1.1.1");
            quarantacinque.addPlayer("Tumbleweed", "1.1.1.1");
        }
        quindici.godChoose("Apollo", "Artemis", "Athena");
        trenta.godChoose("Atlas", "Chronus", "Demeter");
        quarantacinque.godChoose("Hephaestus", "Hera", "Hestia");
        hunger.godChoose("Minotaur", "Pan", "Prometheus");
        quindici.setGod("Apollo");
        quindici.setGod("Artemis");
        quindici.setGod("Athena");
        trenta.setGod("Atlas");
        trenta.setGod("Chronus");
        trenta.setGod("Demeter");
        quarantacinque.setGod("Hephaestus");
        quarantacinque.setGod("Hera");
        quarantacinque.setGod("Hestia");
        hunger.setGod("Minotaur");
        hunger.setGod("Pan");
        hunger.setGod("Prometheus");
    }

    @Test
    public void gamePrep() {
        hunger.addPlayer("Gianni", "1.1.1.1");
        hunger.addPlayer("Vladimir", "11.11.11.11");
        assertEquals(-1, hunger.addPlayer("Dimitri", "111.111.111.111"));
        hunger.godChoose("Triton", "Zeus", "Hera");
        assertEquals(-1, hunger.setGod("Hera"));
    }
}
