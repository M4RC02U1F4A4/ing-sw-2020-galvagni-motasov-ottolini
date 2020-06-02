package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.model.god.Athena;
import it.polimi.ingsw.PSP23.model.god.Hera;
import org.junit.*;
import static org.junit.Assert.*;

public class TurnManagerTest {
    TurnManager IChooseYou;
    Player Bulbasaur, Squirtle, Charmender;

    @Before
    public void setUp() {
        IChooseYou = new TurnManager();
        Bulbasaur = new Player("tizio", "1.1.1.1");
        Bulbasaur.setPlayerNumber(0);
        Squirtle = new Player("caio", "11.11.11.11");
        Squirtle.setPlayerNumber(1);
        Charmender = new Player("sempronio", "111.111.111.111");
        Charmender.setPlayerNumber(2);
    }

    @After
    public void tearDown() {
        IChooseYou = null;
        Bulbasaur = null;
        Squirtle = null;
        Charmender = null;
    }

    @Test
    public void getterSetter() {
        IChooseYou.setPlayerNumber(3);
        assertEquals(3, IChooseYou.getNumberOfPlayers());
        Bulbasaur.setPlayerNumber(1);
        Bulbasaur.setGod(new God());
        IChooseYou.setCurrentPlayer(Bulbasaur);
        assertEquals(0, IChooseYou.getCurrentPlayerNumber());
    }

    @Test
    public void setupPhaseTest() {
        IChooseYou.setPlayerNumber(2);
        // god choose
        IChooseYou.setCurrentPlayer(Bulbasaur);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // god pick player 2
        IChooseYou.setCurrentPlayer(Squirtle);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.GOD_PICK, IChooseYou.getCurrentPhase());
        Squirtle.setGod(new Hera());
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // god pick player 1 and setting worker 0
        IChooseYou.setCurrentPlayer(Bulbasaur);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.GOD_PICK, IChooseYou.getCurrentPhase());
        Bulbasaur.setGod(new Athena());
        IChooseYou.nextPhaseSetUp();
        Bulbasaur.setWorkerByNumber(new Worker(new Cell(), Color.WHITE, 0), 0);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // player 2 worker 0 set
        IChooseYou.setCurrentPlayer(Squirtle);
        IChooseYou.nextPhaseSetUp();
        Squirtle.setWorkerByNumber(new Worker(new Cell(), Color.WHITE, 0), 0);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // player 1 worker 1 set
        IChooseYou.setCurrentPlayer(Bulbasaur);
        IChooseYou.nextPhaseSetUp();
        Bulbasaur.setWorkerByNumber(new Worker(new Cell(), Color.WHITE, 0), 1);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // player 2 worker 1 set
        IChooseYou.setCurrentPlayer(Squirtle);
        IChooseYou.nextPhaseSetUp();
        Squirtle.setWorkerByNumber(new Worker(new Cell(), Color.WHITE, 0), 1);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        // player 1 end
        IChooseYou.setCurrentPlayer(Bulbasaur);
        IChooseYou.nextPhaseSetUp();
        assertEquals(Phase.CHOOSE_WORKER, IChooseYou.getCurrentPhase());
    }

    @Test
    public void gamePhaseTest() {
        IChooseYou.goBanana();
        Bulbasaur.setGod(new God());
        IChooseYou.setPlayerNumber(1);
        Bulbasaur.setPlayerNumber(0);
        IChooseYou.setCurrentPlayer(Bulbasaur);
        assertEquals(Phase.CHOOSE_WORKER, IChooseYou.getCurrentPhase());
        IChooseYou.nextPhaseGame();
        assertEquals(Phase.START_TURN, IChooseYou.getCurrentPhase());
        Bulbasaur.getGod().startTurn(false);
        IChooseYou.nextPhaseGame();
        assertEquals(Phase.CHECK_LOSE_MOVE, IChooseYou.getCurrentPhase());
        Bulbasaur.getGod().remains_moves = 0;
        for (int cont = 0; cont < 5; cont++)
            IChooseYou.nextPhaseGame();
        assertEquals(Phase.CHECK_WIN_BUILD, IChooseYou.getCurrentPhase());
        Bulbasaur.getGod().remains_builds = 0;
        IChooseYou.nextPhaseGame();
        assertEquals(Phase.END, IChooseYou.getCurrentPhase());
        IChooseYou.nextPhaseGame();
        assertEquals(0, IChooseYou.getCurrentPlayerNumber());
    }
}
