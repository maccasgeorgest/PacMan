package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class AmbusherTest {
    @Test
    public void constructor() {
        assertNotNull(new Ambusher(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Ambusher a = new Ambusher(new PImage(), 0, 0);
        assertTrue(a.getName().equals("Ambusher"));
        assertTrue(a.Left() == 0);
        assertTrue(a.Right() == 16);
        assertTrue(a.Top() == 0);
        assertTrue(a.Bottom() == 16);
        assertTrue(a.CentreX() == 8);
        assertTrue(a.CentreY() == 8);
    }
    @Test
    public void setTargetTest() {
        App app = new App();
        GameEvent ge = new GameEvent(app);
        Ambusher a = new Ambusher(new PImage(), 0, 0);
        a.setTarget(ge, true);
        assertEquals(a.targetX, 448);
        assertEquals(a.targetY, 0);
    }
    @Test
    public void switchModeTest() {
        Ambusher a = new Ambusher(new PImage(), 0, 0); 
        assertTrue(a.scatter == false);
        a.switchMode();
        assertTrue(a.scatter == true);
        a.switchMode();
        assertTrue(a.scatter == false);
    }

    @Test
    public void reverseTest() {
        GameEvent ge = new GameEvent(null);
        Ambusher a = new Ambusher(new PImage(), 0, 0);
        assertTrue(a.direction.equals("left"));
        String reverse = a.reverse();
        assertEquals(reverse, "right");
        a.move("right", ge);
        reverse = a.reverse();
        assertEquals(reverse, "left");
        a.move("down", ge);
        reverse = a.reverse();
        assertEquals(reverse, "up");
        a.move("up", ge);
        reverse = a.reverse();
        assertEquals(reverse, "down");
    }
}
