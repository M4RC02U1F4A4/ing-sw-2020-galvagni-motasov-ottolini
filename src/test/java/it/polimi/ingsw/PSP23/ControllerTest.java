package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.controller.Controller;
import it.polimi.ingsw.PSP23.model.Action;
import it.polimi.ingsw.PSP23.model.Game;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.model.PlayerMove;
import org.junit.*;

public class ControllerTest {
    Controller Torre;

    @Before
    public void setUp() {
        Torre = new Controller(new Game(2));
    }

    @After
    public void tearDown() {
        Torre = null;
    }

    @Test
    public void ChooseWorker() {
        Torre.setActionFromTheClient("CHOOSE_WORKER", "0", null);
    }
}
