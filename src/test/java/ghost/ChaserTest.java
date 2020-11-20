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
        // c.setTarget(ge, false);
        // assertEquals(c.targetX, ge.waka.CentreX());
        // assertEquals(c.targetY, ge.waka.CentreY());
    }
}
