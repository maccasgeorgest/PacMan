package ghost;

import org.junit.Test;

import processing.core.PImage;
import static org.junit.Assert.*;

public class WakaTest {
    @Test
    public void constructor() {
        assertNotNull(new Waka(new PImage(), 5, 5));
    } 
}
