package ghost;

import org.junit.Test;

import processing.core.PImage;

import static org.junit.Assert.*;

public class GhostTest {
    @Test
    public void constructor() {
        assertNotNull(new Ghost(new PImage(), 5, 5));
    }
}
