package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class SuperfruitTest {
    @Test
    public void constructor() {
        assertNotNull(new Superfruit(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Superfruit sf = new Superfruit(new PImage(), 0, 0);
        assertTrue(sf.getName().equals("Superfruit"));
        assertTrue(sf.Left() == 0);
        assertTrue(sf.Right() == 16);
        assertTrue(sf.Top() == 0);
        assertTrue(sf.Bottom() == 16);
        assertTrue(sf.CentreX() == 8);
        assertTrue(sf.CentreY() == 8);
    }
}

