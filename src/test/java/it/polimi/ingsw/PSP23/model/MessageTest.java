package it.polimi.ingsw.PSP23.model;

import it.polimi.ingsw.PSP23.view.RemoteView;
import it.polimi.ingsw.PSP23.view.View;
import org.junit.*;

public class MessageTest {
    Message mex;
    PlayerMove move;
    Player player = new Player("Vamos", "1.1.1.1");
    Map map = new Map();
    TextMessage text = new TextMessage();


    @Before
    public void setUp() {
        mex = new Message(player, map, Action.PLACE_WORKER, 1,1,0);
        move = new PlayerMove(player, null, "Gino", "pino");
    }

    @After
    public void tearDown() {
        mex = null;
        move = null;
        player = null;
    }

    @Test
    public void getset() {
        mex.getMap();
        mex.getPlayer();
        mex.getAction();
        mex.getX();
        mex.getY();
        move.getCommand();
        move.getArgs();
        move.getPlayer();
        move.getView();
    }
}
