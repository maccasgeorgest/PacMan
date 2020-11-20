package ghost;

import org.junit.jupiter.api.Test;

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
}
