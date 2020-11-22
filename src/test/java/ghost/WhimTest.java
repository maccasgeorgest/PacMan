package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;


public class WhimTest {
    @Test
    public void constructor() {
        assertNotNull(new Whim(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Whim w = new Whim(new PImage(), 0, 0);
        assertTrue(w.getName().equals("Whim"));
        assertTrue(w.Left() == 0);
        assertTrue(w.Right() == 16);
        assertTrue(w.Top() == 0);
        assertTrue(w.Bottom() == 16);
        assertTrue(w.CentreX() == 8);
        assertTrue(w.CentreY() == 8);
    }
    @Test
    public void setTargetTest() {
        App app = new App();
        GameEvent ge = new GameEvent(app);
        Whim w = new Whim(new PImage(), 0, 0);
        w.setTarget(ge, true);
        assertEquals(w.targetX, 448);
        assertEquals(w.targetY, 576);
    }

    @Test
    public void switchModeTest() {
        Whim w = new Whim(new PImage(), 0, 0); 
        assertTrue(w.scatter == false);
        w.switchMode();
        assertTrue(w.scatter == true);
        w.switchMode();
        assertTrue(w.scatter == false);
    }

    @Test
    public void reverseTest() {
        GameEvent ge = new GameEvent(null);
        Whim w = new Whim(new PImage(), 0, 0);
        assertTrue(w.direction.equals("left"));
        String reverse = w.reverse();
        assertEquals(reverse, "right");
        w.move("right", ge);
        reverse = w.reverse();
        assertEquals(reverse, "left");
        w.move("down", ge);
        reverse = w.reverse();
        assertEquals(reverse, "up");
        w.move("up", ge);
        reverse = w.reverse();
        assertEquals(reverse, "down");
    }
    
    @Test
    public void chaseModeTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.noLoop();
        for (Ghost ghost : app.gameEvent.ghostList) {
            if (ghost.getName().equals("Whim")) {
                ghost.setTarget(app.gameEvent, false);
                assertEquals(ghost.targetX, 0);
                assertEquals(ghost.targetY, 0);
                app.gameEvent.waka.move("left", app.gameEvent);
                ghost.setTarget(app.gameEvent, false);
                assertEquals(ghost.targetX, 168);
                assertEquals(ghost.targetY, 424);
                app.gameEvent.chaser = null;
                ghost.setTarget(app.gameEvent, false);
                assertEquals(ghost.targetX, 216);
                assertEquals(ghost.targetY, 328);
            }
        }
    }

    @Test 
    public void frightenTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.noLoop();
        app.gameEvent.waka.changeVulnerability(true);
        for (Ghost ghost: app.gameEvent.ghostList) {
            assertTrue(ghost.isInvincible());
            assertFalse(ghost.frightened);
            ghost.die(true);
            assertTrue(ghost.isDead());
            ghost.die(false);
            assertFalse(ghost.isDead());
        }
    }

    
}
