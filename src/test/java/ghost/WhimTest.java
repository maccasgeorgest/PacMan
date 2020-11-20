package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;

public class WhimTest {
    @Test
    public void constructor() {
        assertNotNull(new Whim(new PImage(), 5, 5));
    } 

    @Test
    public void getterMethods() {
        Whim w = new Whim(new PImage(), 0, 0);
        assertTrue(w.getName().equals("Whim"));
        assertTrue(w.Left() == 0);
        assertTrue(w.Right() == 16);
        assertTrue(w.Top() == 0);
        assertTrue(w.Bottom() == 16);
        assertTrue(w.CentreX() == 8);
        assertTrue(w.CentreY() == 8);
    }
    @Before
    public void Movement() {
        // GameEvent ge = new GameEvent()
        // Whim w = new Whim(new PImage(), 0, 0);
        // w.move("up");
    }

    
}
