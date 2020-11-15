package ghost;

import org.junit.Test;

import processing.core.PImage;
import static org.junit.Assert.*;

public class FruitTest {
    @Test
    public void constructor() {
        assertNotNull(new Fruit(new PImage(), 5, 5));
    } 
}
