package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

public class GameEventTest {
    @Test
    public void constructor() {
        assertNotNull(new GameEvent(null));
    }
    @Test
    public void debugModeTest() {
        App app = new App();
        GameEvent ge = new GameEvent(app);
        assertFalse(ge.debugMode);
        ge.initiateDebugMode();
        assertTrue(ge.debugMode);
        ge.initiateDebugMode();
        assertFalse(ge.debugMode);
    }

    @Test
    public void gameLogicTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.noLoop();
        app.setup();
        app.gameEvent.restartTime = 600;
        app.gameEvent.restart(app);
        assertEquals(app.gameEvent.restartTime, 0);
        assertEquals(app.gameEvent.ghostList.size(), 4);
        assertEquals(app.gameEvent.fruitCount, 300);
        app.gameEvent.lives = 0;
        assertEquals(app.gameEvent.restartTime, 0);
        assertEquals(app.gameEvent.ghostList.size(), 4);
        assertEquals(app.gameEvent.fruitCount, 300);
    }
}
