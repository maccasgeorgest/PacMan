package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class EmptyTest {
    @Test
    public void constructor() {
        assertNotNull(new EmptyCell(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        EmptyCell e = new EmptyCell(new PImage(), 0, 0);
        assertTrue(e.getName().equals("Empty"));
        assertTrue(e.Left() == 0);
        assertTrue(e.Right() == 16);
        assertTrue(e.Top() == 0);
        assertTrue(e.Bottom() == 16);
        assertTrue(e.CentreX() == 8);
        assertTrue(e.CentreY() == 8);
    }
}
