package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*; 

public class FruitTest {
    @Test
    public void constructor() {
        assertNotNull(new Fruit(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Fruit f = new Fruit(new PImage(), 0, 0);
        assertTrue(f.getName().equals("Fruit"));
        assertTrue(f.Left() == 0);
        assertTrue(f.Right() == 16);
        assertTrue(f.Top() == 0);
        assertTrue(f.Bottom() == 16);
        assertTrue(f.CentreX() == 8);
        assertTrue(f.CentreY() == 8);
    }
}
