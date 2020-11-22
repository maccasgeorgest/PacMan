package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class ChaserTest {
    @Test
    public void constructor() {
        assertNotNull(new Chaser(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Chaser c = new Chaser(new PImage(), 0, 0);
        assertTrue(c.getName().equals("Chaser"));
        assertTrue(c.Left() == 0);
        assertTrue(c.Right() == 16);
        assertTrue(c.Top() == 0);
        assertTrue(c.Bottom() == 16);
        assertTrue(c.CentreX() == 8);
        assertTrue(c.CentreY() == 8);
    }

    @Test
    public void setTargetTest() {
        App app = new App();
        GameEvent ge = new GameEvent(app);
        Chaser c = new Chaser(new PImage(), 0, 0);
        c.setTarget(ge, true);
        assertEquals(c.targetX, 0);
        assertEquals(c.targetY, 0);
    }
    @Test
    public void switchModeTest() {
        Chaser c = new Chaser(new PImage(), 0, 0); 
        assertTrue(c.scatter == false);
        c.switchMode();
        assertTrue(c.scatter == true);
        c.switchMode();
        assertTrue(c.scatter == false);
    }

    @Test
    public void reverseTest() {
        GameEvent ge = new GameEvent(null);
        Chaser c = new Chaser(new PImage(), 0, 0);
        assertTrue(c.direction.equals("left"));
        String reverse = c.reverse();
        assertEquals(reverse, "right");
        c.move("right", ge);
        reverse = c.reverse();
        assertEquals(reverse, "left");
        c.move("down", ge);
        reverse = c.reverse();
        assertEquals(reverse, "up");
        c.move("up", ge);
        reverse = c.reverse();
        assertEquals(reverse, "down");
    }
    @Test
    public void chaseModeTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.noLoop();
        app.setup();
        for (Ghost ghost : app.gameEvent.ghostList) {
            if (ghost.getName().equals("Chaser")) {
                ghost.setTarget(app.gameEvent, false);
                assertEquals(ghost.targetX, 216);
                assertEquals(ghost.targetY, 328);
            }
        } 
    }
    @Test
    public void ghostLogicTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.noLoop();
        app.setup();
        for (Ghost ghost : app.gameEvent.ghostList) {
            if (ghost.getName().equals("Chaser")) {
                ghost.tick(app.gameEvent);
                assertTrue(ghost.isInvincible());
                assertFalse(ghost.frightened);
                assertFalse(app.gameEvent.waka.isInvincible());
                ghost.modeShiftCounter = 299;
                ghost.tick(app.gameEvent);
                assertEquals(ghost.modeShiftCounter, 0);
                assertFalse(ghost.isDead());
                app.gameEvent.waka.changeVulnerability(true);
                ghost.tick(app.gameEvent);
                assertTrue(ghost.frightened);
                app.gameEvent.frightenedCounter = 2399;
                ghost.tick(app.gameEvent);
                assertFalse(ghost.frightened);
                assertFalse(app.gameEvent.waka.isInvincible());
                app.gameEvent.waka.sodaEffect(true);
                ghost.tick(app.gameEvent);
                assertTrue(ghost.invisibleCounter == 1);
                ghost.invisibleCounter = 9;
                ghost.tick(app.gameEvent);
                assertTrue(ghost.invisibleCounter % 10 == 0);
                ghost.invisibleCounter = 299;
                ghost.tick(app.gameEvent);
                assertFalse(app.gameEvent.waka.drunk());
                assertEquals(ghost.invisibleCounter, 0);
            }
        }  
    }
}
