package ghost;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.core.PImage;
import static org.junit.jupiter.api.Assertions.*;

public class WakaTest {
    @Test
    public void constructor() {
        assertNotNull(new Waka(new PImage(), 5, 5));
    } 
    
    @Test
    public void getterMethods() {
        Waka w = new Waka(new PImage(), 0, 0);
        assertTrue(w.getName().equals("Waka"));
        assertTrue(w.Left() == 0);
        assertTrue(w.Right() == 16);
        assertTrue(w.Top() == 0);
        assertTrue(w.Bottom() == 16);
        assertTrue(w.CentreX() == 8);
        assertTrue(w.CentreY() == 8);
    }

    @Test
    public void moveHandlerTest() {
        Waka w = new Waka(new PImage(), 0, 0);
        assertTrue(w.moveAttempt.equals("left"));
        w.moveHandler(38);
        assertTrue(w.moveAttempt.equals("up"));
        w.moveHandler(40);
        assertTrue(w.moveAttempt.equals("down"));
        w.moveHandler(37);
        assertTrue(w.moveAttempt.equals("left"));
        w.moveHandler(39);
        assertTrue(w.moveAttempt.equals("right"));
    }

    @Test
    public void directionTest() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.noLoop();
        app.setup();
        assertEquals(app.gameEvent.waka.direction, "left");
        app.gameEvent.waka.move("right", app.gameEvent);
        assertEquals(app.gameEvent.waka.direction, "right");
        app.gameEvent.waka.move("up", app.gameEvent);
        assertEquals(app.gameEvent.waka.direction, "right");
        app.gameEvent.waka.move("down", app.gameEvent);
        assertEquals(app.gameEvent.waka.direction, "right");
    }

    @Test
    public void sodaDrinkTest() {
        Waka w = new Waka(new PImage(), 0, 0);
        assertEquals(w.drunk(), false);
        w.sodaEffect(true);
        assertEquals(w.drunk(), true);
        w.sodaEffect(false);
        assertEquals(w.drunk(), false);
    }

    @Test
    public void movement() {
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.noLoop();
        app.setup();
    }
}
