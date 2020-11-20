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
}
