package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class SodaTest {
    @Test
    public void constructor() {
        assertNotNull(new Soda(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Soda s = new Soda(new PImage(), 0, 0);
        assertTrue(s.getName().equals("Soda"));
        assertTrue(s.Left() == 0);
        assertTrue(s.Right() == 16);
        assertTrue(s.Top() == 0);
        assertTrue(s.Bottom() == 16);
        assertTrue(s.CentreX() == 8);
        assertTrue(s.CentreY() == 8);
    }
}
