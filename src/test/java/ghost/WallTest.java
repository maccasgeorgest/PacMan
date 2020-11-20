package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class WallTest {
    @Test
    public void constructor() {
        assertNotNull(new Wall(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Wall w = new Wall(new PImage(), 0, 0);
        assertTrue(w.getName().equals("Wall"));
        assertTrue(w.Left() == 0);
        assertTrue(w.Right() == 16);
        assertTrue(w.Top() == 0);
        assertTrue(w.Bottom() == 16);
        assertTrue(w.CentreX() == 8);
        assertTrue(w.CentreY() == 8);
    }
}
