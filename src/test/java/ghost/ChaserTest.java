package ghost;

import org.junit.jupiter.api.Test;

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
}
