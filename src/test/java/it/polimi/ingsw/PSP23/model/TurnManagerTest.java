package it.polimi.ingsw.PSP23.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

public class TurnManagerTest {
    TurnManager IChooseYou;
    Player Pikachu, Squirtle, Charmender;

    @Before
    public void setUp() {
        IChooseYou = new TurnManager();
        Pikachu = new Player("tizio", "1.1.1.1");
        Squirtle = new Player("caio", "11.11.11.11");
        Charmender = new Player("sempronio", "111.111.111.111");
    }

    @After
    public void tearDown() {
        IChooseYou = null;
        Pikachu = null;
        Squirtle = null;
        Charmender = null;
    }

    @Test
    public void getterSetter() {
        IChooseYou.addPlayer();
        assertEquals(1, IChooseYou.getNumberOfPlayers());
        IChooseYou.addPlayer();
        assertEquals(2, IChooseYou.getNumberOfPlayers());
        IChooseYou.subsPlayer();
        assertEquals(1, IChooseYou.getNumberOfPlayers());
        Pikachu.setPlayerNumber(1);
        IChooseYou.setCurrentPlayerNumber(Pikachu);
        assertEquals(1, IChooseYou.getCurrentPlayerNumber());
    }

    @Test
    public void phaseTest() {
        Pikachu.setGod(new God());
        IChooseYou.addPlayer();
        Pikachu.setPlayerNumber(0);
        IChooseYou.setCurrentPlayerNumber(Pikachu);
        assertEquals(Phase.CHOOSE_WORKER, IChooseYou.getCurrentPhase());
        IChooseYou.nextPhase();
        assertEquals(Phase.START_TURN, IChooseYou.getCurrentPhase());
        Pikachu.getGod().startTurn(false);
        IChooseYou.nextPhase();
        assertEquals(Phase.CHECK_LOSE_MOVE, IChooseYou.getCurrentPhase());
        Pikachu.getGod().remains_moves = 0;
        for (int cont = 0; cont < 5; cont++)
            IChooseYou.nextPhase();
        assertEquals(Phase.CHECK_WIN_BUILD, IChooseYou.getCurrentPhase());
        Pikachu.getGod().remains_builds = 0;
        IChooseYou.nextPhase();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        IChooseYou.nextPhase();
        assertEquals(0, IChooseYou.getCurrentPlayerNumber());
    }
}
