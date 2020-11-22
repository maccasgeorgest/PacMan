package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class IgnorantTest {
    @Test
    public void constructor() {
        assertNotNull(new Ignorant(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Ignorant i = new Ignorant(new PImage(), 0, 0);
        assertTrue(i.getName().equals("Ignorant"));
        assertTrue(i.Left() == 0);
        assertTrue(i.Right() == 16);
        assertTrue(i.Top() == 0);
        assertTrue(i.Bottom() == 16);
        assertTrue(i.CentreX() == 8);
        assertTrue(i.CentreY() == 8);
    }
    @Test
    public void setTargetTest() {
        App app = new App();
        GameEvent ge = new GameEvent(app);
        Ignorant i = new Ignorant(new PImage(), 0, 0);
        i.setTarget(ge, true);
        assertEquals(i.targetX, 0);
        assertEquals(i.targetY, 576);
    }

    @Test
    public void switchModeTest() {
        Ignorant i = new Ignorant(new PImage(), 0, 0); 
        assertTrue(i.scatter == false);
        i.switchMode();
        assertTrue(i.scatter == true);
        i.switchMode();
        assertTrue(i.scatter == false);
    }

    @Test
    public void reverseTest() {
        GameEvent ge = new GameEvent(null);
        Ignorant i = new Ignorant(new PImage(), 0, 0);
        assertTrue(i.direction.equals("left"));
        String reverse = i.reverse();
        assertEquals(reverse, "right");
        i.move("right", ge);
        reverse = i.reverse();
        assertEquals(reverse, "left");
        i.move("down", ge);
        reverse = i.reverse();
        assertEquals(reverse, "up");
        i.move("up", ge);
        reverse = i.reverse();
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
            if (ghost.getName().equals("Ignorant")) {
                ghost.setTarget(app.gameEvent, false);
                assertEquals(ghost.targetX, 0);
                assertEquals(ghost.targetY, 576);
            }
        } 
    }
}
