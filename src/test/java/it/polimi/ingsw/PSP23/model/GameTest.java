package it.polimi.ingsw.PSP23.model;

import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {
    Game hunger;

    @Before
    public void setUp() {
        hunger = new Game();
    }

    @After
    public void tearDown() {
        hunger = null;
    }

    @Test
    public void getterSetter() {
    }
}
